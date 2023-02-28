package sync

import (
	"bytes"
	"fmt"
	"io"
	"sync"
	"testing"
)

var bufPool sync.Pool

type Buffer interface {
	Delimiter() byte
	Write(contents string) error
	Read() (Contents string, err error)
	Free()
}

type myBuffer struct {
	buf       bytes.Buffer
	delimiter byte
}

func (b *myBuffer) Delimiter() byte {
	return b.delimiter
}

func (b *myBuffer) Write(contents string) error {
	_, err := b.buf.WriteString(contents)
	if err != nil {
		return err
	}
	return b.buf.WriteByte(b.Delimiter())
}

func (b *myBuffer) Read() (string, error) {
	return b.buf.ReadString(b.delimiter)
}

func (b *myBuffer) Free() {
	bufPool.Put(b)
}

var delimiter = byte('\n')

func setPool() {
	bufPool = sync.Pool{
		New: func() interface{} {
			return &myBuffer{delimiter: delimiter}
		},
	}
}

func GetBuffer() Buffer {
	return bufPool.Get().(Buffer)
}

func TestPool(t *testing.T) {
	setPool()
	buf := GetBuffer()
	defer buf.Free()
	buf.Write("one.")
	buf.Write("two.")
	buf.Write("three.")
	fmt.Println("buffers:")
	for {
		block, err := buf.Read()
		if err != nil {
			if err == io.EOF {
				break
			}
			panic(fmt.Errorf("unexpect err:%s", err))
		}
		fmt.Print(block)
	}
}
