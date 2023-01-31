package leet

import (
	"fmt"
	"math/bits"
	"strconv"
	"testing"
)

func TestLeetCode4(t *testing.T) {
	// res := countPairs([]int{3, 1, 2, 2, 2, 1, 3}, 2)
	// res := countEven(1000)
	// res := prefixCount([]string{"pay", "attention", "practice", "attend"}, "at")
	// res := mostFrequent([]int{2, 2, 2, 2, 3}, 2)
	// res := cellsInRange("K1:L2")
	// res := findKDistantIndices([]int{734, 228, 636, 204, 552, 732, 686, 461, 973, 874, 90, 537, 939, 986, 855, 387, 344, 939, 552, 389, 116, 93, 545, 805, 572, 306, 157, 899, 276, 479, 337, 219, 936, 416, 457, 612, 795, 221, 51, 363, 667, 112, 686, 21, 416, 264, 942, 2, 127, 47, 151, 277, 603, 842, 586, 630, 508, 147, 866, 434, 973, 216, 656, 413, 504, 360, 990, 228, 22, 368, 660, 945, 99, 685, 28, 725, 673, 545, 918, 733, 158, 254, 207, 742, 705, 432, 771, 578, 549, 228, 766, 998, 782, 757, 561, 444, 426, 625, 706, 946}, 939, 34)
	// res := findKDistantIndices([]int{2, 2, 2, 2, 2}, 2, 2)
	// res := divideArray([]int{2, 2, 3, 3, 55, 55, 3})
	// res := countHillValley([]int{21, 21, 21, 2, 2, 2, 2, 21, 21, 45})
	// res := findDifference([]int{1, 3, 3}, []int{2, 4, 6})
	// res := convertTime("02:30", "04:35")
	res := largestInteger(65875)
	t.Log(res)
}

// 2231
func largestInteger(num int) int {
	var n1 = [10]int{}
	str := strconv.Itoa(num)
	var ans = make([]int, len(str))
	var checker = make([]bool, len(str))
	for i := 0; i < len(str); i++ {
		n1[str[i]-'0']++
		if (str[i]-'0')%2 == 0 {
			checker[i] = true
		}
	}
	index := 0
	for i := 9; i >= 0; i-- {
		for n1[i] > 0 {
			ans[index] = i
			n1[i]--
			index += 2
		}
	}
	var res int
	for _, n := range ans {
		res = res*10 + n
	}
	return res
}

// 2224
func convertTime(current string, correct string) int {
	curr := getMinute(current)
	corr := getMinute(correct)
	num := corr - curr
	return num/60 + (num%60)/15 + (num%15)/5 + num%5
}

func getMinute(str string) int {
	return int((str[0]-'0')*10+(str[1]-'0'))*60 + int((str[3]-'0')*10+str[4]-'0')
}

// 2220
func minBitFlips(start int, goal int) int {
	return bits.OnesCount(uint(start ^ goal))
}

// 2215
func findDifference(nums1 []int, nums2 []int) [][]int {
	var m1 = map[int]bool{}
	var m2 = map[int]bool{}
	for _, n2 := range nums2 {
		m2[n2] = true
	}
	var ans [][]int
	var t1 []int
	var t2 []int
	for _, n1 := range nums1 {
		m1[n1] = true
	}
	for n2, _ := range m2 {
		if !m1[n2] {
			t2 = append(t2, n2)
		}
	}
	for n1, _ := range m1 {
		if !m2[n1] {
			t1 = append(t1, n1)
		}
	}
	ans = append(ans, t1, t2)
	return ans
}

// 2210
func countHillValley(nums []int) int {
	var fil []int
	fil = append(fil, nums[0])
	for i := 1; i < len(nums); i++ {
		if nums[i] == nums[i-1] {
			continue
		}
		fil = append(fil, nums[i])
	}
	var ans int
	for i := 1; i < len(fil)-1; i++ {
		if fil[i-1] < fil[i] && fil[i+1] < fil[i] {
			ans++
		}
		if fil[i-1] > fil[i] && fil[i+1] > fil[i] {
			ans++
		}
	}
	return ans

	/*var ans int
	var offset = 1
	for i := 1; i < len(nums)-1; i++ {
		if nums[i] == nums[i-1] {
			offset++
		}
		if i < offset {
			offset = 1
			continue
		}
		if nums[i-offset] < nums[i] && nums[i+1] < nums[i] {
			ans++
		}
		if nums[i-offset] > nums[i] && nums[i+1] > nums[i] {
			ans++
		}
		offset = 1
	}
	return ans*/
}

// 2206
func divideArray(nums []int) bool {
	var map1 = map[int]int{}
	for _, num := range nums {
		map1[num]++
	}
	for _, n2 := range map1 {
		if n2%2 != 0 {
			return false
		}
	}
	return true

	/*var bucket = [501]int{}
	for _, num := range nums {
		bucket[num]++
	}
	for _, n2 := range bucket {
		if n2%2 != 0 {
			return false
		}
	}
	return true*/
}

// 2200
func findKDistantIndices(nums []int, key int, k int) []int {
	var tt = make([]bool, len(nums))
	var ans []int
	for i := 0; i < len(nums); i++ {
		if nums[i] == key {
			tt[i] = true
			var t = 1
			for t <= k {
				if i >= t {
					tt[i-t] = true
				}
				if i+t < len(nums) {
					tt[i+t] = true
				}
				t++
			}
		}
	}
	for index, checker := range tt {
		if checker {
			ans = append(ans, index)
		}
	}
	return ans

	/*var ans []int
	max := -1
	for i := 0; i < len(nums); i++ {
		if nums[i] == key {
			tmp := k
			for tmp >= 1 {
				if i >= tmp && i-tmp > max {
					ans = append(ans, i-tmp)
				}
				tmp--
			}
			if i > max {
				ans = append(ans, i)
			}
			tmp = 1
			for tmp <= k {
				if i+tmp < len(nums) && i+tmp > max {
					ans = append(ans, i+tmp)
					max = i + tmp
				}
				tmp++
			}
		}
	}
	return ans*/
}

// 2194
func cellsInRange(s string) []string {
	minN := s[1] - '0'
	maxN := s[4] - '0'
	minR := s[0]
	maxR := s[3]
	var ans = make([]string, (maxN-minN+1)*(maxR-minR+1))
	var index int
	for minR <= maxR {
		tmp := minN
		for tmp <= maxN {
			ans[index] = fmt.Sprintf("%c%d", minR, tmp)
			index++
			tmp++
		}
		minR++
	}
	return ans
}

// 2190
func mostFrequent(nums []int, key int) int {
	var targetMap = map[int]int{}
	preN := nums[0]
	for i := 1; i < len(nums); i++ {
		if preN == key {
			targetMap[nums[i]]++
		}
		preN = nums[i]
	}
	var maxCnt int
	var ans int
	for num, cnt := range targetMap {
		if cnt > maxCnt {
			ans = num
			maxCnt = cnt
		}
	}
	return ans
}

// 2185
func prefixCount(words []string, pref string) int {
	var count int
	for _, word := range words {
		if len(word) < len(pref) {
			continue
		}
		var checkRes = true
		for i := 0; i < len(pref); i++ {
			if pref[i] != word[i] {
				checkRes = false
				break
			}
		}
		if checkRes {
			count++
		}
	}
	return count
}

// 2180
func countEven(num int) int {
	var ans int
	n1 := num / 10
	n2 := n1 / 10
	n3 := n1 % 10
	n5 := n2 / 10
	ans += n1 * 5
	n4 := num % 10
	if (n2+n3+n5)%2 != 0 {
		ans--
		n4++
	}
	ans += n4 / 2
	return ans
}

// 2176
func countPairs(nums []int, k int) int {
	var pair int
	for i := 0; i < len(nums)-1; i++ {
		for j := i + 1; j < len(nums); j++ {
			if nums[j] != nums[i] {
				continue
			}
			if i*j%k == 0 {
				pair++
			}
		}
	}
	return pair
}

// 2169
func countOperations(num1 int, num2 int) int {
	var ans int
	if num1 == 0 || num2 == 0 {
		return ans
	}
	for {
		ans++
		if num1 > num2 {
			num1 -= num2
		} else if num1 < num2 {
			num2 -= num1
		} else {
			break
		}
	}
	return ans
}
