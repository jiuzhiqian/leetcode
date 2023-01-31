package leet

import (
	"fmt"
	"sort"
	"strconv"
	"testing"
)

func TestLeetCode5(t *testing.T) {
	// res := haveConflict([]string{"10:00", "11:00"}, []string{"09:00", "09:30"})
	// res := oddString([]string{"abm", "bcn", "alm"})
	// res := applyOperations([]int{1, 2, 2, 1, 1, 0})
	// res := distinctAverages([]int{4, 1, 4, 0, 3, 5})
	// res := convertTemperature(36.50)
	// res := unequalTriplets([]int{4, 4, 2, 4, 3})
	// res := numberOfCuts(3)
	// res := pivotInteger(1)
	// res := isCircularSentence("leetcoda exercises sound delightful")
	// res := maximumValue([]string{"alic3", "bob", "3", "4", "00000"})
	// res := deleteGreatestValue([][]int{{1, 2, 4}, {3, 3, 1}})
	res := similarPairs([]string{"aabb", "ab", "ba"})
	t.Log(res)
}


// 2506
func similarPairs(words []string) int {
	var mm = map[[26]bool]int{}
	for _, word := range words {
		var arr = [26]bool{}
		for _, char := range word {
			arr[char-'a'] = true
		}
		mm[arr]++
	}
	var ans int
	for _, cnt := range mm {
		if cnt < 2 {
			continue
		}
		ans += (cnt - 1) * cnt / 2
	}
	return ans
}

// 2500
func deleteGreatestValue(grid [][]int) int {
	for key, _ := range grid {
		sort.Ints(grid[key])
	}
	var ans int
	for i := 0; i < len(grid[0]); i++ {
		var max int
		for j := 0; j < len(grid); j++ {
			if max < grid[j][i] {
				max = grid[j][i]
			}
		}
		ans += max
	}
	return ans
}

// 2496
func maximumValue(strs []string) int {
	var ans int
	for _, str := range strs {
		isDig := true
		for _, char := range str {
			if char < '0' || char > '9' {
				isDig = false
				break
			}
		}
		if isDig {
			num, _ := strconv.Atoi(str)
			if ans < num {
				ans = num
			}
		} else {
			if ans < len(str) {
				ans = len(str)
			}
		}
	}
	return ans
}

// 2490
func isCircularSentence(sentence string) bool {
	lenth := len(sentence)
	if sentence[0] != sentence[lenth-1] {
		return false
	}
	for i := 1; i < lenth-1; i++ {
		if sentence[i] == ' ' && sentence[i-1] != sentence[i+1] {
			return false
		}
	}
	return true
}

// 2485
func pivotInteger(n int) int {
	sum := (n + 1) * n / 2
	var t1 = sum
	var t2 = 0
	for t1 > t2 && n > 0 {
		t2 += n
		t1 = sum - t2 + n
		if t1 == t2 {
			return n
		}
		n--
	}
	return -1
}

// 2481
func numberOfCuts(n int) int {
	if n == 1 {
		return 0
	}
	if n%2 == 0 {
		return n / 2
	} else {
		return n
	}
}

// 2475
// 在 xx 之前遍历过的数有 aa 个；
// （当前遍历的）等于 xx 的数有 bb 个；
// 在 xx 之后遍历过的数有 cc 个。
func unequalTriplets(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	a, c := 0, len(nums)
	for _, b := range cnt {
		c -= b
		ans += a * b * c
		a += b
	}
	return
}

// 2469 ???
func convertTemperature(celsius float64) []float64 {
	return []float64{celsius + 273.15, celsius*1.8 + 32}
}

// 2465
func distinctAverages(nums []int) int {
	var mm = map[float32]int{}
	var arr = [101]int{}
	for _, num := range nums {
		arr[num]++
	}
	left := 0
	right := 100
	for left <= right {
		if arr[left] == 0 {
			left++
			continue
		} else if arr[right] == 0 {
			right--
			continue
		}
		mm[float32(left+right)/2] = 1
		arr[left]--
		arr[right]--
	}
	return len(mm)
}

// 2460
func applyOperations(nums []int) []int {
	var noZero int
	for i := 0; i < len(nums)-1; i++ {
		if nums[i] == nums[i+1] {
			nums[i] *= 2
			nums[i+1] = 0
		}
		if nums[i] != 0 {
			noZero++
		}
	}
	if nums[len(nums)-1] != 0 {
		noZero++
	}
	index := 0
	for i := 0; i < len(nums); i++ {
		if nums[i] != 0 {
			nums[index] = nums[i]
			index++
		}
		if i >= noZero {
			nums[i] = 0
		}
	}
	return nums
}

// 2455
func averageValue(nums []int) int {
	var cnt, sum int
	for _, num := range nums {
		if num%6 != 0 {
			continue
		}
		cnt++
		sum += num
	}
	if cnt == 0 {
		return 0
	}
	return sum / cnt
}

// 2451
func oddString(words []string) string {
	var map1 = map[string]int{}
	var map2 = map[string]string{}
	for _, word := range words {
		var str = ""
		for i := 0; i < len(word)-1; i++ {
			str += fmt.Sprintf("%d\t", int(word[i+1])-int(word[i]))
		}
		map1[str]++
		map2[word] = str
	}
	var res string
	for str, cnt := range map1 {
		if cnt == 1 {
			res = str
			break
		}
	}
	for word, str := range map2 {
		if res == str {
			return word
		}
	}
	return ""
}

// 2446
func haveConflict(event1 []string, event2 []string) bool {
	m1 := getMinute2(event1[0])
	m2 := getMinute2(event1[1])
	m3 := getMinute2(event2[0])
	m4 := getMinute2(event2[1])
	return !(m1 > m4 || m3 > m2)
}

func getMinute2(str string) int {
	return int((str[0]-'0')*10+(str[1]-'0'))*60 + int((str[3]-'0')*10+str[4]-'0')
}
