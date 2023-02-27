package atomic

import (
	"fmt"
	"sync/atomic"
	"testing"
	"time"
)

func TestAtomic(t *testing.T) {
	num := uint32(18)
	delta := int32(-3)

	atomic.AddUint32(&num, uint32(delta))
	atomic.AddUint32(&num, uint32(delta))
	//t.Logf("num:%d", num)
	atomic.AddUint32(&num, ^uint32(-(-3)-1))
	//t.Logf("num_2:%d", num)

	//fmt.Printf("The two's complement of %d: %b\n",
	//	delta, uint32(delta)) // -3的补码。
	//fmt.Printf("The equivalent: %b\n", ^uint32(-(-3)-1)) // 与-3的补码相同。

	forAndCAS2()
}

func forAndCAS1() {
	sign := make(chan struct{}, 2)
	num := int32(0)
	fmt.Println("num:", num)
	go func() {
		defer func() {
			sign <- struct{}{}
		}()
		for {
			time.Sleep(500 * time.Millisecond)
			newNum := atomic.AddInt32(&num, 2)
			fmt.Println("num_r:", newNum)
			if newNum == 10 {
				break
			}
		}
	}()

	go func() {
		defer func() {
			sign <- struct{}{}
		}()
		for {
			if atomic.CompareAndSwapInt32(&num, 10, 0) {
				fmt.Println("num goes to zero")
				break
			}
			time.Sleep(500 * time.Millisecond)
		}
	}()
	<-sign
	<-sign
}

func forAndCAS2() {
	sign := make(chan struct{}, 2)
	num := int32(0)
	fmt.Println("num:", num)
	max := int32(20)
	go func(id int, max int32) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; ; i++ {
			currNum := atomic.LoadInt32(&num)
			if currNum >= max {
				break
			}
			newNum := currNum + 2
			fmt.Println("new_num_1:", newNum)
			time.Sleep(200 * time.Millisecond)
			if atomic.CompareAndSwapInt32(&num, currNum, newNum) {
				fmt.Printf("The number: %d [%d-%d]\n", newNum, id, i)
			} else {
				fmt.Printf("The CAS operation failed. [%d-%d]\n", id, i)
			}
		}
	}(1, max)

	go func(id int, max int32) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; ; i++ {
			currNum := atomic.LoadInt32(&num)
			if currNum >= max {
				break
			}
			newNum := currNum + 2
			fmt.Println("new_num_2:", newNum)
			time.Sleep(200 * time.Millisecond)
			if atomic.CompareAndSwapInt32(&num, currNum, newNum) {
				fmt.Printf("The number: %d [%d-%d]\n", newNum, id, i)
			} else {
				fmt.Printf("The CAS operation failed. [%d-%d]\n", id, i)
			}
		}
	}(2, max)
}
