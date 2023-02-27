package main

import (
	"bytes"
	"errors"
	"fmt"
	"io"
	"sync"
	"time"
)

type singleHandler func() (data string, n int, err error)

type handlerConfig struct {
	handle    singleHandler
	goNum     int
	number    int
	interval  time.Duration
	counter   int
	counterMu sync.Mutex
}

func (hc *handlerConfig) count(increment int) int {
	hc.counterMu.Lock()
	defer hc.counterMu.Unlock()
	hc.counter += increment
	return hc.counter

}

func main() {
	// 使用即可，不要在函数中传递
	var mu sync.Mutex
	genWriter := func(writer io.Writer) singleHandler {
		return func() (data string, n int, err error) {
			data = fmt.Sprintf("%s\t", time.Now().Format(time.StampNano))
			// 写入数据
			mu.Lock()
			defer mu.Unlock()
			n, err = writer.Write([]byte(data))
			return
		}
	}

	genReader := func(reader io.Reader) singleHandler {
		return func() (data string, n int, err error) {
			buffer, ok := reader.(*bytes.Buffer)
			if !ok {
				err = errors.New("reader err")
				return
			}
			mu.Lock()
			defer mu.Unlock()
			data, err = buffer.ReadString('\t')
			n = len(data)
			return
		}
	}

	var buffer bytes.Buffer

	writeConfig := handlerConfig{
		handle:   genWriter(&buffer),
		goNum:    5,
		number:   4,
		interval: time.Millisecond * 100,
	}

	readConfig := handlerConfig{
		handle:   genReader(&buffer),
		goNum:    5,
		number:   4,
		interval: time.Millisecond * 100,
	}

	sign := make(chan struct{}, writeConfig.goNum+readConfig.goNum)
	for i := 0; i < writeConfig.goNum; i++ {
		go func(i int) {
			defer func() {
				sign <- struct{}{}
			}()
			for j := 0; j < writeConfig.number; j++ {
				time.Sleep(writeConfig.interval)
				data, n, err := writeConfig.handle()
				if err != nil {
					fmt.Printf("writer [%d-%d]: error:%s\n", i, j, err)
					continue
				}
				total := writeConfig.count(n)
				fmt.Printf("write [%d-%d]: %s (total:%d)\n", i, j, data, total)
			}
		}(i)
	}

	for i := 0; i < readConfig.goNum; i++ {
		go func(i int) {
			defer func() {
				sign <- struct{}{}
			}()
			for j := 0; j < readConfig.number; j++ {
				time.Sleep(readConfig.interval)
				var data string
				var n int
				var err error
				for {
					data, n, err = readConfig.handle()
					if err != nil || err != io.EOF {
						break
					}
					time.Sleep(readConfig.interval)
				}
				if err != nil {
					fmt.Printf("read [%d-%d]: error:%s (total:%d)\n", i, j, err)
				}
				total := readConfig.count(n)
				fmt.Printf("read [%d-%d]: %s (total:%d)\n", i, j, data, total)
			}
		}(i)
	}
	// 等待全部的缓冲通道可取，所有goroutine全部结束
	for i := 0; i < writeConfig.goNum+readConfig.goNum; i++ {
		<-sign
	}
}
