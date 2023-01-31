package leet

import (
	"math"
	"sort"
	"strconv"
	"testing"
)

func TestLeetCode0122(t *testing.T) {
	//res := findClosestNumber([]int{-4, -2, 1, 4, 8})
	//res := countAsterisks("l|*e*et|c**o|*de|")
	//res := countDigits(123)
	//res := findMissingRanges([]int{0, 1, 3, 50, 75}, 0, 100)
	//res := summaryRanges([]int{0, 1, 3, 50, 74, 75})
	//res := shortestDistance([]string{"practice", "makes", "perfect", "coding", "makes"}, "practice", "makes")
	//res := isStrobogrammatic("661899")
	//res := canAttendMeetings([][]int{{2, 6}, {8, 9}})
	//res := generatePossibleNextMoves("+-++")
	//res := validWordAbbreviation("internationalization", "i12iz5")
	//res := validWordSquare([]string{"abc", "b"})
	//res := findPoisonedDuration([]int{1, 2}, 2)
	res := countBinarySubstrings("00110011")
	t.Log(res)
}

// 696
func countBinarySubstrings(s string) int {
	var ptr, last, ans int
	n := len(s)
	for ptr < n {
		c := s[ptr]
		count := 0
		for ptr < n && s[ptr] == c {
			ptr++
			count++
		}
		ans += min(count, last)
		last = count
	}
	return ans
}

// 495
func findPoisonedDuration(timeSeries []int, duration int) int {
	var ans int
	if len(timeSeries) == 0 {
		return ans
	}
	ans += duration
	for i := 0; i < len(timeSeries)-1; i++ {
		if timeSeries[i]+duration <= timeSeries[i+1] {
			ans += duration
		} else {
			ans += timeSeries[i+1] - timeSeries[i]
		}
	}
	return ans
}

// 422
func validWordSquare(words []string) bool {
	ll := len(words)
	wordMaxLen := math.MinInt
	for i := 0; i < ll; i++ {
		if len(words[i]) > wordMaxLen {
			wordMaxLen = len(words[i])
		}
		for j := 0; j < ll; j++ {
			// 判断点对点且不越界
			if j < len(words[i]) && i < len(words[j]) {
				if words[i][j] != words[j][i] {
					return false
				}
			} else if j >= len(words[i]) && i >= len(words[j]) {
				break
			} else {
				return false
			}
		}
	}
	if wordMaxLen != ll {
		return false
	}
	return true
}

// 408
func validWordAbbreviation(word string, abbr string) bool {
	index, curr := 0, 0
	for i := 0; i < len(abbr); i++ {
		if abbr[i] >= '0' && abbr[i] <= '9' {
			if curr == 0 && abbr[i] == '0' {
				return false
			}
			curr = curr*10 + int(abbr[i]-'0')
		} else {
			if curr > 0 {
				index += curr
			}
			curr = 0
			if index > len(word)-1 {
				return false
			}
			if word[index] != abbr[i] {
				return false
			}
			index++
		}
	}
	if curr > 0 {
		index += curr
	}
	return index == len(word)
}

// 293
func generatePossibleNextMoves(currentState string) []string {
	var ans []string
	for i := 0; i < len(currentState)-1; i++ {
		if currentState[i] != '+' {
			continue
		}
		if currentState[i+1] != '+' {
			i++
			continue
		}
		ans = append(ans, currentState[:i]+"--"+currentState[i+2:])
	}
	return ans
}

// 252
func canAttendMeetings(intervals [][]int) bool {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	for i := 0; i < len(intervals)-1; i++ {
		if intervals[i][1] > intervals[i+1][0] {
			return false
		}
	}
	return true
}

// 246
func isStrobogrammatic(num string) bool {
	ll := len(num)
	for i := 0; i < (ll+1)/2; i++ {
		j := ll - i - 1
		if num[i] == '6' || num[i] == '9' {
			if num[j] != num[i] && (num[j] == '9' || num[j] == '6') {
				continue
			}
		}
		if num[i] == '0' || num[i] == '1' || num[i] == '8' {
			if num[i] == num[j] {
				continue
			}
		}
		return false
	}
	return true
}

//243
func shortestDistance(wordsDict []string, word1 string, word2 string) int {
	var distance = math.MaxInt
	k1, k2 := -1, -1
	for i := 0; i < len(wordsDict); i++ {
		if wordsDict[i] == word1 {
			k1 = i
		} else if wordsDict[i] == word2 {
			k2 = i
		} else {
			continue
		}
		if k1 >= 0 && k2 >= 0 {
			tmpDis := k1 - k2
			if tmpDis < 0 {
				tmpDis = -tmpDis
			}
			if tmpDis < distance {
				distance = tmpDis
			}
		}
	}
	return distance
}

// 228
func summaryRanges(nums []int) []string {
	var ans []string
	if len(nums) == 0 {
		return ans
	}
	joinStr := "->"
	lower, upper := nums[0], nums[0]
	for i := 0; i < len(nums); i++ {
		if nums[i] > upper+1 {
			if lower == upper {
				ans = append(ans, strconv.Itoa(lower))
			} else {
				ans = append(ans, strconv.Itoa(lower)+joinStr+strconv.Itoa(upper))
			}
			lower, upper = nums[i], nums[i]
		} else {
			upper = nums[i]
		}
	}
	if lower == upper {
		ans = append(ans, strconv.Itoa(lower))
	} else {
		ans = append(ans, strconv.Itoa(lower)+joinStr+strconv.Itoa(upper))
	}
	return ans
}

// 163
func findMissingRanges(nums []int, lower int, upper int) []string {
	var ans []string
	var curr int
	joinStr := "->"
	for i := 0; i < len(nums); i++ {
		if nums[i] >= upper {
			if upper == nums[i] {
				upper--
			}
			break
		}
		if lower > nums[i] {
			curr = nums[i] + 1
		} else if lower == nums[i] {
			lower = nums[i] + 1
			curr = nums[i] + 1
		} else {
			curr = nums[i] - 1
			if lower == curr {
				ans = append(ans, strconv.Itoa(lower))
			} else {
				ans = append(ans, strconv.Itoa(lower)+joinStr+strconv.Itoa(curr))
			}
			lower = nums[i] + 1
		}
	}
	if lower < upper {
		ans = append(ans, strconv.Itoa(lower)+joinStr+strconv.Itoa(upper))
	} else if lower == upper {
		ans = append(ans, strconv.Itoa(lower))
	}
	return ans
}

// 2520
func countDigits(num int) int {
	var ans int
	for i := num; i > 0; i /= 10 {
		if num%(i%10) == 0 {
			ans++
		}
	}
	return ans
}

// 2315
func countAsterisks(s string) int {
	isBegin := false
	ans, curr := 0, 0
	for _, cc := range s {
		if cc == '|' {
			isBegin = !isBegin
		} else if cc == '*' {
			if isBegin {
				curr++
			} else {
				ans++
			}
		}
	}
	if isBegin {
		ans += curr
	}
	return ans
}

// 2239
func findClosestNumber(nums []int) int {
	var ans int
	var distance = math.MaxInt
	for _, num := range nums {
		var tmp int
		if num > 0 {
			tmp = num
		} else {
			tmp = -num
		}
		if tmp < distance {
			distance = tmp
			ans = num
		} else if tmp == distance && num > ans {
			ans = num
		}
	}
	return ans
}

// 2235
func sum(num1 int, num2 int) int {
	return num1 + num2
}
