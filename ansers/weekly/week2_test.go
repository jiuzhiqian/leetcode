package weekly

import (
	"testing"
)

func TestWeekly2(t *testing.T) {
	var res interface{}
	res = numSpecial([][]int{{0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}})
	//res = stringShift("yisxjwry", [][]int{{1, 8}, {1, 4}, {1, 3}, {1, 6}, {0, 6}, {1, 4}, {0, 2}, {0, 1}})
	//res = tictactoe([][]int{{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}})
	//res = countElements([]int{1, 1, 3, 3, 5, 5, 7, 6})
	t.Log(res)
}

// 1582
func numSpecial(mat [][]int) int {
	l1, l2, ans := len(mat), len(mat[0]), 0
	col, line := make([]int, l1), make([]int, l1)
	for i := 0; i < l1; i++ {
		for j := 0; j < l2; j++ {
			if mat[i][j] > 0 {
				col[j]++
				line[i]++
			}
		}
	}
	for i := 0; i < l1; i++ {
		if line[i] != 1 {
			continue
		}
		for j := 0; j < l2; j++ {
			if col[j] != 1 {
				continue
			}
			if mat[i][j] == 1 {
				ans++
			}
		}
	}
	return ans
}

// 1427
func stringShift(s string, shift [][]int) string {
	var ll int
	for _, arr := range shift {
		if arr[0] == 0 {
			ll += arr[1]
		} else {
			ll -= arr[1]
		}
	}
	ll %= len(s)
	if ll == 0 {
		return s
	}
	if ll < 0 {
		ll = len(s) + ll
	}
	return s[ll:] + s[0:ll]
}

// 1426
func countElements(arr []int) int {
	mm := map[int]int{}
	ans, ll := 0, len(arr)
	for i := 0; i < ll; i++ {
		if mm[arr[i]+1] > 0 {
			ans++
		}
		if mm[arr[i]] == 0 {
			ans += mm[arr[i]-1]
		}
		mm[arr[i]]++
	}
	return ans
}

// 1275
func tictactoe(moves [][]int) string {
	arr := [9]int{}
	for kk, move := range moves {
		var val int
		if kk&1 == 0 {
			val = 1
		} else {
			val = -1
		}
		arr[move[0]*3+move[1]] = val
		// 判断横竖斜
		checkLine, checkColumn, isWin := true, true, false
		for i := 0; i < 3; i++ {
			if arr[move[0]*3+i] != val {
				checkLine = false
			}
			if arr[i*3+move[1]] != val {
				checkColumn = false
			}
		}
		isWin = checkLine || checkColumn
		if move[0]%3 == move[1]%3 || move[0]+move[1] == 2 {
			if (arr[0] == val && arr[4] == val && arr[8] == val) || (arr[2] == val && arr[4] == val && arr[6] == val) {
				isWin = true
			}
		}
		if isWin {
			if val > 0 {
				return "A"
			} else {
				return "B"
			}
		}
	}
	if len(moves) < 9 {
		return "Pending"
	} else {
		return "Draw"
	}
}
