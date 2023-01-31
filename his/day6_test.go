package leet

import (
	"math"
	"sort"
	"testing"
)

func TestLeetCode6(t *testing.T) {
	//res := minCount([]int{2, 3, 10})
	//res := calculate("AB")
	//res := breakfastNumber([]int{6, 1, 9, 2, 9, 9, 3, 4}, []int{2, 7, 10, 2, 9, 2, 1, 3}, 2)
	//res := areNumbersAscending("1 box has 3 blue 4 red 6 green and 12 yellow marbles")
	//res := search([]int{4, 5, 6, 7, 0, 1, 2}, 0)
	//res := maxValue(4, 2, 6)
	//t.Log(res)
}


// 33
func search(nums []int, target int) int {
	left := 0
	right := len(nums) - 1
	var mid int
	for left < right {
		if nums[left] == target {
			return left
		} else if nums[right] == target {
			return right
		}
		mid = (left + right + 1) / 2
		if nums[mid] == target {
			return mid
		}
	}
	return -1
}

// 2042
func areNumbersAscending(s string) bool {
	var pre uint8
	var curr uint8
	ll := len(s)
	for i := 0; i < ll-1; i++ {
		if s[i] > '9' || s[i] < '0' {
			curr = 0
		} else {
			curr = (s[i] - '0') + curr*10
			if s[i+1] == ' ' {
				if curr <= pre {
					return false
				}
				pre = curr
			}
		}
	}
	if s[ll-1] >= '0' && s[ll-1] <= '9' {
		curr = (s[ll-1] - '0') + curr*10
		return !(curr <= pre)
	}
	return true
}

// lcp 18
func breakfastNumber(staple []int, drinks []int, x int) int {
	sort.Ints(drinks)
	sort.Ints(staple)
	var ans int
	index := 100000
	curr := len(drinks)
	var arr = [100001]int{}
	//var arr = [10]int{}
	for _, drink := range drinks {
		arr[drink]++
	}
	for _, st := range staple {
		if st > x {
			break
		}
		max := x - st
		for i := index; i > max; i-- {
			curr -= arr[i]
			index--
		}
		//fmt.Println(st, index, curr)
		ans += curr
	}
	return ans % 1000000007
}

// lcp 17
func calculate(s string) int {
	return int(math.Pow(float64(2), float64(len(s))))
	x := 1
	y := 0
	for _, char := range s {
		if char == 'A' {
			x = 2*x + y
		} else {
			y = 2*y + x
		}
	}
	return x + y
}

// lcp 06
func minCount(coins []int) int {
	var ans int
	for _, coin := range coins {
		ans += (coin + 1) / 2
	}
	return ans
}
