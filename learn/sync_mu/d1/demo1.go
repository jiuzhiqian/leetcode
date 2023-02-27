package main

import (
	"bytes"
	"fmt"
	"io"
	"sync"
)

func main() {
	var buffer bytes.Buffer
	const (
		max1 = 5
		max2 = 10
		max3 = 10
	)
	var mu sync.Mutex
	sign := make(chan struct{}, max1)
	for i := 0; i < max1; i++ {
		go func(id int, writer io.Writer) {
			defer func() {
				sign <- struct{}{}
			}()
			for j := 0; j < max2; j++ {
				header := fmt.Sprintf("\n[id:%d,iteration:%d]", id, j)
				data := fmt.Sprintf(" %d", id*j)
				// 写入数据
				mu.Lock()
				_, err := writer.Write([]byte(header))
				if err != nil {
					fmt.Printf("error:%s [%d]\n", err, id)
				}
				for k := 0; k < max3; k++ {
					_, err = writer.Write([]byte(data))
					if err != nil {
						fmt.Printf("error:%s [%d]\n", err, id)
					}
				}
				mu.Unlock()
			}
		}(i, &buffer)
	}

	// 遍历等待channel都结束
	for i := 0; i < max1; i++ {
		<-sign
	}
	data, err := io.ReadAll(&buffer)
	if err != nil {
		fmt.Printf("fatal err:%s\n", err)
	}
	fmt.Printf("The content:\n%s\n", data)

}
