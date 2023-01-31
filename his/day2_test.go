package leet

import (
	"sort"
	"testing"
)

func TestLeetCode2(t *testing.T) {
	// candis := []int{2, 3, 6, 7}
	// res := combinationSum(candis, 7)
	// res := multiply("25", "25")
	// res := jump([]int{2, 3, 1, 1, 4})
	// res := groupAnagrams([]string{"eat", "tea", "tan", "ate", "nat", "bat"})
	res := canJump([]int{3, 2, 1, 1, 4})
	t.Log(res)
}

func getNum(n, i, j int) int {
	return n*i + j + 1
}

func insert(intervals [][]int, newInterval []int) (ans [][]int) {
	// 有交集则有添加空间吸收交集，知道没有交集
	left, right := newInterval[0], newInterval[1]
	merged := false
	for _, interval := range intervals {
		if interval[0] > right {
			// 在插入区间的右侧且无交集
			if !merged {
				ans = append(ans, []int{left, right})
				merged = true
			}
			ans = append(ans, interval)
		} else if interval[1] < left {
			// 在插入区间的左侧且无交集
			ans = append(ans, interval)
		} else {
			// 与插入区间有交集，计算它们的并集
			left = min(left, interval[0])
			right = max(right, interval[1])
		}
	}
	if !merged {
		ans = append(ans, []int{left, right})
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

// 57
func insert2(intervals [][]int, newInterval []int) [][]int {
	intervals = append(intervals, newInterval)
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	var ans [][]int
	ans = append(ans, intervals[0])
	index, ll := 0, len(intervals)
	for i := 1; i < ll; i++ {
		if intervals[i][0] > ans[index][1] {
			index++
			ans = append(ans, intervals[i])
		} else if intervals[i][1] > ans[index][1] {
			ans[index][1] = intervals[i][1]
		}
	}
	return ans
}

// 56
func merge(intervals [][]int) [][]int {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	var ans [][]int
	ans = append(ans, intervals[0])
	index := 0
	for i := 1; i < len(intervals); i++ {
		if intervals[i][0] > ans[index][1] {
			index++
			ans = append(ans, intervals[i])
		} else if intervals[i][1] > ans[index][1] {
			ans[index][1] = intervals[i][1]
		}
	}
	return ans
}

func max(i, j int) int {
	if i > j {
		return i
	}
	return j
}

// 55
func canJump(nums []int) bool {
	maxPos, ll := 0, len(nums)
	for i := 0; i < ll; i++ {
		if i > maxPos {
			return false
		}
		if i+nums[i] > maxPos {
			maxPos = i + nums[i]
		}
	}
	return true
}

// 54
func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 {
		return []int{}
	}
	var ans []int
	row := len(matrix[0])
	list := len(matrix)
	left, right, top, down := 0, row, 0, list
	for len(ans) < row*list {
		for i := left; i < right; i++ {
			ans = append(ans, matrix[top][i])
		}
		for i := top + 1; i < down; i++ {
			ans = append(ans, matrix[i][right-1])
		}
		if left < right-1 && top < down-1 {
			for i := right - 2; i >= left; i-- {
				ans = append(ans, matrix[down-1][i])
			}
			for i := down - 2; i > left; i-- {
				ans = append(ans, matrix[i][left])
			}
		}
		left++
		right--
		top++
		down--
	}
	return ans
}

func spiralOrder2(matrix [][]int) []int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return []int{}
	}
	var (
		rows, columns            = len(matrix), len(matrix[0])
		order                    = make([]int, rows*columns)
		index                    = 0
		left, right, top, bottom = 0, columns - 1, 0, rows - 1
	)

	for left <= right && top <= bottom {
		for column := left; column <= right; column++ {
			order[index] = matrix[top][column]
			index++
		}
		for row := top + 1; row <= bottom; row++ {
			order[index] = matrix[row][right]
			index++
		}
		if left < right && top < bottom {
			for column := right - 1; column > left; column-- {
				order[index] = matrix[bottom][column]
				index++
			}
			for row := bottom; row > top; row-- {
				order[index] = matrix[row][left]
				index++
			}
		}
		left++
		right--
		top++
		bottom--
	}
	return order
}

// 50
func myPow(x float64, n int) float64 {
	if n >= 0 {
		return quickMul(x, n)
	}
	return 1.0 / quickMul(x, -n)
}

func quickMul(x float64, n int) float64 {
	ans := 1.0
	if n == 0 {
		return ans
	}
	curr := x
	for n > 0 {
		if n%2 == 1 {
			ans *= curr
		}
		curr *= curr
		n /= 2
	}
	return ans
}

// 49
func groupAnagrams(strs []string) [][]string {
	strMap := map[[26]uint8][]string{}
	for _, str := range strs {
		arr := [26]uint8{}
		for _, c := range str {
			arr[int(c-'a')]++
		}
		strMap[arr] = append(strMap[arr], str)
	}
	ans := make([][]string, 0, len(strMap))
	for _, val := range strMap {
		ans = append(ans, val)
	}
	return ans
}

// 48
func rotate(matrix [][]int) {
	ll := len(matrix)
	for i := 0; i < ll>>1; i++ {
		for j := 0; j < (ll+1)>>1; j++ {
			matrix[i][j], matrix[ll-j-1][i], matrix[ll-i-1][ll-j-1], matrix[j][ll-i-1] =
				matrix[ll-j-1][i], matrix[ll-i-1][ll-j-1], matrix[j][ll-i-1], matrix[i][j]
		}
	}
}

var res [][]int

func permute(nums []int) [][]int {
	res = [][]int{}
	backTrack(nums, len(nums), []int{})
	return res
}

func backTrack(nums []int, numsLen int, path []int) {
	if len(nums) == 0 {
		p := make([]int, len(path))
		copy(p, path)
		res = append(res, p)
	}
	for i := 0; i < numsLen; i++ {
		cur := nums[i]
		path = append(path, cur)
		nums = append(nums[:i], nums[i+1:]...) // 直接使用切片
		backTrack(nums, len(nums), path)
		nums = append(nums[:i], append([]int{cur}, nums[i:]...)...) // 回溯的时候切片也要复原，元素位置不能变
		path = path[:len(path)-1]
	}
}
