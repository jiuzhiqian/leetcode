package sync

import (
	"fmt"
	"sync"
	"sync/atomic"
	"testing"
)

func TestDemo3(t *testing.T) {
	var wg sync.WaitGroup
	wg.Add(2)
	num := int32(0)
	max := int32(10)
	go addNum(&num, 3, max, wg.Done)
	go addNum(&num, 4, max, wg.Done)
	wg.Wait()
	fmt.Println(num, max)
}

func addNum(num *int32, id, max int32, deferFunc func()) {
	defer func() {
		deferFunc()
	}()
	for i := 0; ; i++ {
		currNum := atomic.LoadInt32(num)
		if currNum >= max {
			break
		}
		newNum := currNum + 2
		if atomic.CompareAndSwapInt32(num, currNum, newNum) {
			fmt.Printf("The number: %d [%d-%d]\n", newNum, id, i)
		} else {
			fmt.Println("operation fail by id:", id)
		}
	}
}
