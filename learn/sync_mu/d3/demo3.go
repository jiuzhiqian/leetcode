package main

import (
	"log"
	"sync"
	"time"
)

type counter struct {
	num uint
	mu  sync.RWMutex
}

func (c *counter) number() uint {
	c.mu.RLock()
	defer c.mu.RUnlock()
	return c.num
}

func (c *counter) add(incr uint) uint {
	c.mu.Lock()
	defer c.mu.Unlock()
	c.num += incr
	return c.num
}

func count(c *counter) {
	sign := make(chan struct{}, 3)
	go func() {
		defer func() {
			sign <- struct{}{}
		}()
		for j := 0; j < 10; j++ {
			time.Sleep(500 * time.Millisecond)
			c.add(1)
			//log.Printf("The number in counter: %d [%d-%d]", c.number(), 1, j)
		}
	}()
	go func() {
		defer func() {
			sign <- struct{}{}
		}()
		for j := 0; j < 20; j++ {
			time.Sleep(200 * time.Millisecond)
			log.Printf("The number in counter: %d [%d-%d]", c.number(), 2, j)
		}
	}()
	go func() {
		defer func() {
			sign <- struct{}{}
		}()
		for j := 0; j < 20; j++ {
			time.Sleep(300 * time.Millisecond)
			log.Printf("The number in counter: %d [%d-%d]", c.number(), 3, j)
		}
	}()
	for i := 0; i < 3; i++ {
		<-sign
	}
}

func redundantUnlock() {
	var rwMu sync.RWMutex
	//rwMu.Unlock()	// panic 1
	//rwMu.RUnlock() // panic 2

	rwMu.RLock()
	//rwMu.Unlock() // panic 3
	rwMu.RUnlock()

	rwMu.Lock()
	//rwMu.RUnlock() // panic 4
	rwMu.Unlock()

}

func main() {
	c := counter{}
	count(&c)
	redundantUnlock()
}
