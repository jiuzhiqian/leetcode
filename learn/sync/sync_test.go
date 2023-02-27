package sync

import (
	"sync"
	"testing"
	"time"
)

func TestT(t *testing.T) {
	var lock sync.RWMutex
	lock.Lock()
	lock.RLock()
}

func TestDemo1(t *testing.T) {
	var mailBox uint8
	var lock sync.RWMutex
	// 发件变量
	sendCond := sync.NewCond(&lock)
	// 收件变量
	recvCond := sync.NewCond(lock.RLocker())
	// 信号传递
	sign := make(chan struct{}, 2)
	max := 5
	// 发信
	go func(max int) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; i < max; i++ {
			time.Sleep(500 * time.Millisecond)
			lock.Lock() // 持有锁，而非锁定...搞不懂~~
			for mailBox == 1 {
				sendCond.Wait()
			}
			t.Logf("sender [%d]: empty mailbox", i)
			mailBox = 1
			t.Logf("sender:[%d], send letter", i)
			lock.Unlock()
			recvCond.Signal()
		}
	}(max)
	// 首信
	go func(max int) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; i < max; i++ {
			time.Sleep(600 * time.Millisecond)
			lock.RLock()
			for mailBox == 0 {
				t.Logf("recerver [%d] wait", i)
				recvCond.Wait()
			}
			t.Logf("receiver [%d]: mailbox empty", i)
			mailBox = 0
			t.Logf("receiver [%d]: letter recerived", i)
			lock.RUnlock()
			sendCond.Signal()
		}
	}(max)
	<-sign
	<-sign
}

func TestDemo2(t *testing.T) {
	var mailBox uint8
	var lock sync.RWMutex
	// 发件变量
	sendCond := sync.NewCond(&lock)
	// 收件变量
	recvCond := sync.NewCond(&lock)

	send := func(id int, index int) {
		lock.Lock()
		for mailBox == 1 {
			sendCond.Wait()
		}
		t.Logf("sender [%d-%d]: letter send", id, index)
		lock.Unlock()
		recvCond.Broadcast()
	}

	recv := func(id, index int) {
		lock.Lock()
		for mailBox == 0 {
			recvCond.Wait()
		}
		t.Logf("receiver [%d-%d]: letter received", id, index)
		mailBox = 0
		lock.Unlock()
		sendCond.Signal()
	}
	max := 6
	sign := make(chan struct{}, 3)
	go func(id, max int) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; i < max; i++ {
			time.Sleep(500 * time.Millisecond)
			send(id, i)
		}
	}(0, max)

	go func(id, max int) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; i < max; i++ {
			time.Sleep(200 * time.Millisecond)
			recv(id, i)
		}
	}(1, max/2)

	go func(id, max int) {
		defer func() {
			sign <- struct{}{}
		}()
		for i := 0; i < max; i++ {
			time.Sleep(200 * time.Millisecond)
			recv(id, i)
		}
	}(2, max/2)
	<-sign
	<-sign
	<-sign
}
