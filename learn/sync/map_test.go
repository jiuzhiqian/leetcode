package sync

import (
	"fmt"
	"sync"
	"testing"
)

type ConcurrentMap struct {
	m  map[interface{}]interface{}
	mu sync.RWMutex
}

func NewConcurrentMap() *ConcurrentMap {
	return &ConcurrentMap{
		m: make(map[interface{}]interface{}),
	}
}

func (m *ConcurrentMap) Delete(key interface{}) {
	m.mu.Lock()
	defer m.mu.Unlock()
	delete(m.m, key)
}

func (m *ConcurrentMap) Load(key interface{}) (value interface{}, ok bool) {
	m.mu.RLock()
	defer m.mu.RUnlock()
	value, ok = m.m[key]
	return
}

func (m *ConcurrentMap) LoadOrStore(key, value interface{}) (interface{}, bool) {
	m.mu.Lock()
	defer m.mu.Unlock()
	actual, loaded := m.m[key]
	if loaded {
		return actual, loaded
	}
	m.m[key] = value
	return value, loaded
}

func (m *ConcurrentMap) Range(f func(key, value interface{}) bool) {
	m.mu.RLock()
	defer m.mu.RUnlock()
	for k, v := range m.m {
		if !f(k, v) {
			break
		}
	}
}

func (m *ConcurrentMap) Store(key, value interface{}) {
	m.mu.Lock()
	defer m.mu.Unlock()
	m.m[key] = value
}

func TestMap1(t *testing.T) {
	pairs := []struct {
		k int
		v string
	}{
		{k: 1, v: "a"},
		{k: 2, v: "b"},
		{k: 3, v: "c"},
		{k: 4, v: "d"},
	}
	{
		cMap := NewConcurrentMap()
		cMap.Store(pairs[0].k, pairs[0].v)
		cMap.Store(pairs[1].k, pairs[1].v)
		cMap.Store(pairs[2].k, pairs[2].v)
		fmt.Println("store 3 pairs")
		cMap.Range(func(key, value interface{}) bool {
			fmt.Printf("range: %v,%v", key, value)
			return true
		})
		k0 := pairs[0].k
		v0, ok := cMap.Load(k0)
		fmt.Printf("load_result: %v,%v %v", k0, v0, ok)

		k3 := pairs[3].k
		k2, v2 := pairs[2].k, pairs[2].v
		v3, ok := cMap.Load(k3)
		fmt.Printf("The result of Load: %v, %v (key: %v)\n",
			v3, ok, k3)
		v4, ok := cMap.Load(k3)
		fmt.Printf("The result of Load: %v, %v (key: %v)\n",
			v4, ok, k3)

		actual2, loaded2 := cMap.LoadOrStore(k2, v2)
		fmt.Printf("The result of LoadOrStore: %v, %v (key: %v, value: %v)\n",
			actual2, loaded2, k2, v2)
		v3 = pairs[3].v
		actual3, loaded3 := cMap.LoadOrStore(k3, v3)
		fmt.Printf("The result of LoadOrStore: %v, %v (key: %v, value: %v)\n",
			actual3, loaded3, k3, v3)
	}
}

type IntStrMap struct {
	m sync.Map
}

func (iMap *IntStrMap) Delete(key int) {
	iMap.m.Delete(key)
}

func (iMap *IntStrMap) Load(key int) (string, bool) {
	v, ok := iMap.m.Load(key)
	var value string
	if v != nil {
		value = v.(string)
	}
	return value, ok
}

func (iMap *IntStrMap) LoadAndStore(key int, value string) (string, bool) {
	actual, loaded := iMap.m.LoadOrStore(key, value)
	return actual.(string), loaded
}

func (iMap *IntStrMap) Range(ff func(key int, value string) bool) {
	f1 := func(key, value interface{}) bool {
		return ff(key.(int), value.(string))
	}
	iMap.m.Range(f1)
}

func (iMap *IntStrMap) Store(key int, value string) {
	iMap.m.Store(key, value)
}

func TestMap2(t *testing.T) {
	var m1 sync.Map
	m1.Store("zz", "ff")

	k1, v1 := m1.Load("zz")
	fmt.Println(k1, v1)
	k2, v2 := m1.Load("zz")
	fmt.Println(k2, v2)
}
