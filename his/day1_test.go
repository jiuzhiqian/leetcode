package leet

import (
	"sort"
	"strconv"
	"testing"
)

func TestLeetCode(t *testing.T) {
	// candis := []int{2, 3, 6, 7}
	// res := combinationSum(candis, 7)
	// res := multiply("25", "25")
	// res := jump([]int{2, 3, 1, 1, 4})
	res := searchInsert([]int{1, 2, 3, 4}, 4)
	t.Log(res)
}

// 35
func searchInsert(nums []int, target int) int {
	left, right := 0, len(nums)-1
	var res int
	for left < right {
		mid := (right-left)>>1 + left
		if nums[mid] == target {
			res = mid
			break
		} else if nums[mid] > target {
			res = mid
			right = mid - 1
		} else {
			left = mid + 1
		}
	}
	return res
}

// 45
func jump(nums []int) int {
	ll := len(nums)
	end, pos, step := 0, 0, 0
	for i := 0; i < ll-1; i++ {
		if i+nums[i] > pos {
			pos = i + nums[i]
		}
		if i == end {
			end = pos
			step++
		}
	}
	return step
}

// 43
func multiply(num1 string, num2 string) string {
	if num2 == "0" || num1 == "0" {
		return "0"
	}
	l1, l2 := len(num1), len(num2)
	arr := make([]int, l1+l2)
	for i := l1 - 1; i >= 0; i-- {
		ni := int(num1[i]) - '0'
		for j := l2 - 1; j >= 0; j-- {
			nj := int(num2[j]) - '0'
			arr[i+j] += ni * nj
		}
	}
	for i := l1 + l2 - 2; i > 0; i-- {
		arr[i-1] += arr[i] / 10
		arr[i] %= 10
	}
	begin := false
	ans := ""
	for i := 0; i < l1+l2-1; i++ {
		if !begin && arr[i] == 0 {
			continue
		}
		begin = true
		ans += strconv.Itoa(arr[i])
	}
	return ans
}

// 39
var result [][]int

func combinationSum(candidates []int, target int) [][]int {
	if len(candidates) == 0 {
		return nil
	}
	result = make([][]int, 0, len(candidates))
	nums := make([]int, 0, len(candidates))
	// 排序
	sort.Ints(candidates)
	arrangeSum(candidates, nums, target, 0)
	return result
}

func arrangeSum(candidates, nums []int, target int, sum int) {
	// 判断终止条件
	if sum >= target {
		return
	}
	for _, v := range candidates {
		if (len(nums)) > 0 && (nums[len(nums)-1] > v) {
			continue
		}
		nums = append(nums, v)
		sum += v
		arrangeSum(candidates, nums, target, sum)
		if sum == target {
			temp := make([]int, len(nums))
			copy(temp, nums)
			result = append(result, temp)
		}
		sum -= v
		nums = nums[:len(nums)-1]
	}
}

// 36 有效数独
func isValidSudoku(board [][]byte) bool {
	var rows, columns [9][9]int
	var subBox [3][3][9]int
	for i, row := range board {
		for j, char := range row {
			if char == '.' {
				continue
			}
			index := char - '1'
			if rows[i][index] > 0 || columns[j][index] > 0 || subBox[i/3][j/3][index] > 0 {
				return false
			}
			rows[i][index]++
			columns[j][index]++
			subBox[i/3][j/3][index]++
		}
	}
	return true
}

// 32 动态相加，轮训两次，栈
func longestValidParentheses(s string) int {
	maxAns := 0
	// 双向遍历
	// 栈
	/*lastValid := 0
	lastLen := 0
	var stack []int
	for index, val := range s {
		if val == '(' {
			stack = append(stack, index)
		} else {
			if len(stack) == 0 {
				continue
			} else {
				left := stack[len(stack)-1]
				stack = stack[:len(stack)-1]

				if lastValid == left-1 {
					lastValid = index
					lastLen = index - left + 1 + lastLen
				}else {
					lastValid = index
					lastLen = index - left + 1
				}
				if lastLen > maxAns {
					maxAns = lastLen
				}
			}
		}
	}*/
	// 动态相加
	/*dp := make([]int, len(s))
	for i := 1; i < len(s); i++ {
		if s[i] == ')' {
			if s[i-1] == '(' {
				if i > 2 {
					dp[i] = dp[i-2] + 2
				} else {
					dp[i] = 2
				}
			} else if i-dp[i-1] > 0 && s[i-dp[i-1]]-1 == '(' {
				if i-dp[i-1] >= 2 {
					// 牛逼，还有一层加 dp[i-dp[i-1]-2] 这个的操作
					dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
				} else {
					dp[i] = dp[i-1] + 2
				}
			}
			if maxAns > dp[i] {
				maxAns = dp[i]
			}
		}
	}*/
	return maxAns
}

// 32 o(n2)
// func longestValidParentheses(s string) int {
// 	maxLen := 1
// 	var aa = make([][]bool, len(s))
// 	var curr int
// 	for i := 0; i < len(s)-1; i++ {
// 		if s[i] == ')' {
// 			continue
// 		}
// 		curr = 1
// 		for j := i + 1; j < len(s); j++ {
// 			if s[j] == '(' {
// 				curr++
// 			} else {
// 				curr--
// 			}
// 			if curr == 0 {
// 				if j-i+1 > maxLen {
// 					maxLen = j - i + 1
// 				}
// 				aa[i][j] = true
// 			} else if curr < 0 {
// 				break
// 			}
// 		}
// 	}
// 	return maxLen
// }
