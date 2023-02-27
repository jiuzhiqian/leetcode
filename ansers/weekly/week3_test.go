package weekly

import (
	"testing"
)

func TestWeekly3(t *testing.T) {
	var res interface{}
	res = badSensor([]int{2, 3, 2, 2, 3, 2}, []int{2, 3, 2, 3, 2, 7})
	//res = decode([]int{6, 2, 7, 3}, 4)
	t.Log(res)
}

// 1826
func badSensor(sensor1 []int, sensor2 []int) int {
	ll := len(sensor1)
	var ans = -1
	for i := 0; i < ll-1; i++ {
		if sensor2[i] == sensor1[i] {
			continue
		}
		check1 := true
		for j := i; j < ll-1; j++ {
			if sensor1[i] == sensor2[i+1] {
				continue
			} else {
				check1 = false
				break
			}
		}
		if check1 {
			ans = 2
		} else {
			ans = 1
		}
	}
	return ans
}

// 1720
func decode(encoded []int, first int) []int {
	ll := len(encoded)
	var ans = make([]int, ll+1)
	ans[0] = first
	for i := 0; i < ll; i++ {
		ans[i+1] = ans[i] ^ encoded[i]
	}
	return ans
}

// 1791
func findCenter(edges [][]int) int {
	if edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] {
		return edges[0][0]
	}
	return edges[0][1]
}
