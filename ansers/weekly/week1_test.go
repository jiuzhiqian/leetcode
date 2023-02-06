package weekly

import (
	"fmt"
	"math"
	"sort"
	"strconv"
	"testing"
)

func TestWeekly1(t *testing.T) {
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
	//res := countBinarySubstrings("00110011")
	//res := findShortestSubArray([]int{2, 3, 2, 4, 6})
	//res := anagramMappings([]int{12, 28, 46, 32, 50}, []int{50, 12, 32, 46, 28})
	//res := rotateString("abcde", "cdeab")
	//res := similarRGB("#09f166")
	//res := largestTriangleArea([][]int{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {2, 0}})
	//res := uniqueMorseRepresentations([]string{"gin", "zen", "gig", "msg"})
	//res := confusingNumber(11)
	//res := fixedPoint([]int{-10, -5, 0, 3, 7})
	//res := indexPairs("ababa", []string{"aba", "ab"})
	//res := sumOfDigits([]int{99, 77, 33, 66, 55})
	//res := highFive([][]int{{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}})
	//res := twoSumLessThanK([]int{254, 914, 110, 900, 147, 441, 209, 122, 571, 942, 136, 350, 160, 127, 178, 839, 201, 386, 462, 45, 735, 467, 153, 415, 875, 282, 204, 534, 639, 994, 284, 320, 865, 468, 1, 838, 275, 370, 295, 574, 309, 268, 415, 385, 786, 62, 359, 78, 854, 944}, 200)
	//res := numberOfDays(2000, 2)
	//res := removeVowels("leetcodeisacommunityforcoders")
	//res := largestUniqueNumber([]int{5, 7, 3, 9, 4, 9, 8, 3, 1})
	//res := isArmstrong(2)
	//res := isMajorityElement([]int{438885258, 438885258}, 438885258)
	//res := calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode")
	//res := dietPlanPerformance([]int{6, 13, 8, 7, 10, 1, 12, 11}, 6, 5, 37)
	//res := countLetters("aaaba")
	//res := maxNumberOfApples([]int{900, 950, 800, 1000, 700, 800})
	//res := arraysIntersection([]int{1, 2, 3, 4, 5}, []int{1, 2, 5, 7, 9}, []int{1, 3, 4, 5, 9})
	//res := missingNumber([]int{7, 5, 3})
	//res := transformArray([]int{2, 2, 1, 1, 1, 2, 2, 1})
	res := toHexspeak("747823223228")
	t.Log(res)
}

// 1271
func toHexspeak(num string) string {
	intNum, _ := strconv.Atoi(num)
	arr := [16]byte{'O', 'I', 0, 0, 0, 0, 0, 0, 0, 0, 'A', 'B', 'C', 'D', 'E', 'F'}
	var ans []byte
	for intNum > 0 {
		tmpMod := intNum % 16
		if arr[tmpMod] == 0 {
			return "ERROR"
		}
		ans = append([]byte{arr[tmpMod]}, ans...)
		intNum /= 16
	}
	return string(ans)
}

// 1243
func transformArray(arr []int) []int {
	changed := false
	pre := arr[0]
	for i := 1; i < len(arr)-1; i++ {
		if arr[i] > pre && arr[i] > arr[i+1] {
			pre = arr[i]
			arr[i]--
			changed = true
		} else if arr[i] < pre && arr[i] < arr[i+1] {
			pre = arr[i]
			arr[i]++
			changed = true
		} else {
			pre = arr[i]
		}
	}
	if !changed {
		return arr
	}
	return transformArray(arr)
}

// 1228
func missingNumber(arr []int) int {
	curr := arr[1] - arr[0]
	for i := 1; i < len(arr)-1; i++ {
		if arr[i+1]-arr[i] == curr {
			continue
		} else if 2*curr == arr[i+1]-arr[i] {
			return arr[i] + curr
		} else {
			return arr[i] - curr/2
		}
	}
	return arr[0] - curr
}

// 1213
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) []int {
	a1, a2, a3, l1, l2, l3 := 0, 0, 0, len(arr1), len(arr2), len(arr3)
	var ans []int
	for a1 < l1 && a2 < l2 && a3 < l3 {
		//fmt.Println(a1, arr1[a1], a2, arr2[a2], a3, arr3[a3])
		if arr1[a1] == arr2[a2] && arr2[a2] == arr3[a3] {
			ans = append(ans, arr1[a1])
			a1++
			a2++
			a3++
		} else if arr1[a1] < arr2[a2] || arr1[a1] < arr3[a3] {
			a1++
		} else if arr2[a2] < arr1[a1] || arr2[a2] < arr3[a3] {
			a2++
		} else {
			a3++
		}
	}
	return ans
}

// 1196
func maxNumberOfApples(weight []int) int {
	sort.Slice(weight, func(i, j int) bool {
		return weight[i] < weight[j]
	})
	sum := 0
	for i, w := range weight {
		sum += w
		if sum > 5000 {
			return i
		}
	}
	return len(weight)
	/*sort.Ints(weight)
	curr, cnt, maxWei := 0, 0, 5000
	for _, wei := range weight {
		curr += wei
		if curr > maxWei {
			break
		}
		cnt++
	}
	return cnt*/
}

// 1180
func countLetters(s string) int {
	pre, cnt, ans := s[0], 1, 0
	for i := 1; i < len(s); i++ {
		if s[i] == pre {
			cnt++
		} else {
			ans += (cnt + 1) * cnt / 2
			cnt = 1
			pre = s[i]
		}
	}
	ans += (cnt + 1) * cnt / 2
	return ans
}

// 1176
func dietPlanPerformance(calories []int, k int, lower int, upper int) int {
	currKey, currSum, ans := 0, 0, 0
	for i := 0; i < k; i++ {
		currSum += calories[i]
	}
	if currSum > upper {
		ans++
	} else if currSum < lower {
		ans--
	}
	for i := k; i < len(calories); i++ {
		currSum += calories[i] - calories[currKey]
		currKey++
		if currSum > upper {
			ans++
		} else if currSum < lower {
			ans--
		}
	}
	return ans
}

// 1165
func calculateTime(keyboard string, word string) int {
	arr := [26]int{}
	for i, cc := range keyboard {
		arr[cc-'a'] = i
	}
	curr, ans := 0, 0
	for _, cc := range word {
		t := arr[cc-'a']
		if t > curr {
			ans += t - curr
		} else {
			ans += curr - t
		}
		curr = t
	}
	return ans
}

// 1150,GG,边界没处理好
func isMajorityElement(nums []int, target int) bool {
	mid := len(nums) >> 1
	if nums[mid] != target {
		return false
	}
	l, r, ll := mid-1, mid+1, len(nums)
	for l >= 0 && r < ll && (nums[l] == target || nums[r] == target) {
		if nums[l] == target {
			l--
		}
		if nums[r] == target {
			r++
		}
	}
	return (r - l - 1) > ll>>1
}

// 1134
func isArmstrong(n int) bool {
	sum, oriN, cnt := float64(0), float64(n), 0.0
	for n > 0 {
		cnt++
		n /= 10
	}
	n = int(oriN)
	for n > 0 {
		sum += math.Pow(float64(n%10), cnt)
		n /= 10
	}
	return sum == oriN
}

// 1133
func largestUniqueNumber(nums []int) int {
	var arr [1000]int
	for _, num := range nums {
		arr[num]++
	}
	var ans = -1
	for i, n := range arr {
		if n == 1 {
			ans = i
		}
	}
	return ans
}

// 1119
func removeVowels(s string) string {
	var ans []byte
	for i := 0; i < len(s); i++ {
		if s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u' {
			continue
		}
		ans = append(ans, s[i])
	}
	return string(ans)
}

// 1118
func numberOfDays(year int, month int) int {
	arr := [12]int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	if month != 2 {
		return arr[month-1]
	}
	if year%4 == 0 {
		if year%100 != 0 {
			arr[1]++
		} else if year%400 == 0 {
			arr[1]++
		}
	}
	return arr[1]
}

// 1099
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	maxSum := -1
	l, r := 0, len(nums)-1
	for l < r {
		//fmt.Println(l, r, nums[r], nums[l], maxSum)
		if nums[l]+nums[r] >= k {
			r--
		} else {
			if nums[l]+nums[r] > maxSum {
				maxSum = nums[l] + nums[r]
			}
			l++
		}
	}
	return maxSum
}

// 1086
func highFive(items [][]int) [][]int {
	var ans [][]int
	sort.Slice(items, func(i, j int) bool {
		if items[i][0] < items[j][0] {
			return true
		} else if items[i][0] == items[j][0] {
			return items[i][1] > items[j][1]
		} else {
			return false
		}
	})
	var currId = -1
	for i := 0; i < len(items); i++ {
		if items[i][0] != currId {
			currId = items[i][0]
			ans = append(ans, []int{currId, (items[i][1] + items[i+1][1] + items[i+2][1] + items[i+3][1] + items[i+4][1]) / 5})
			i += 4
		}
	}
	return ans
}

// 1085
func sumOfDigits(nums []int) int {
	minSum, minNum := 0, math.MaxInt
	for _, num := range nums {
		if num < minNum {
			minNum = num
		}
	}
	for minNum > 0 {
		minSum += minNum % 10
		minNum /= 10
	}
	return 1 - minSum&1
}

// 1065
func indexPairs(text string, words []string) [][]int {
	var ans [][]int
	ll := len(text)
	for i := 0; i < ll; i++ {
		for _, word := range words {
			if len(word)+i > ll {
				continue
			}
			if text[i:len(word)+i] == word {
				ans = append(ans, []int{i, len(word) + i - 1})
			}
		}
	}
	sort.Slice(ans, func(i, j int) bool {
		if ans[i][0] < ans[j][0] {
			return true
		} else if ans[i][0] == ans[j][0] {
			return ans[i][1] < ans[j][1]
		} else {
			return false
		}
	})
	return ans
}

// 1064
func fixedPoint(arr []int) int {
	var ans = -1
	l, r := 0, len(arr)-1
	for l <= r {
		m := (l + r + 1) >> 1
		if arr[m] == m {
			ans = m
			r = m - 1
		} else if arr[m] > m {
			r = m - 1
		} else {
			l = m + 1
		}
	}
	return ans
}

// 1056
func confusingNumber(n int) bool {
	arr := [10]int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	resNum, oriNum := 0, n
	for n > 0 {
		tmp := n % 10
		if arr[tmp] < 0 {
			return false
		}
		resNum = resNum*10 + arr[tmp]
		n /= 10
	}
	return resNum != oriNum
}
func confusingNumber2(n int) bool {
	numStr := strconv.Itoa(n)
	ll := len(numStr)
	for _, cc := range numStr {
		if cc == '0' || cc == '8' || cc == '1' || cc == '6' || cc == '9' {
			continue
		}
		return false
	}

	for i, cc := range numStr {
		j := ll - i - 1
		if i > j {
			break
		}
		if cc == '6' && numStr[j] == '9' {
			continue
		} else if cc == '9' && numStr[j] == '6' {
			continue
		} else if (cc == '0' || cc == '8' || cc == '1') && cc == int32(numStr[j]) {
			continue
		}
		return true
	}
	return false
}

// 804
func uniqueMorseRepresentations(words []string) int {
	arr := []string{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}
	var mm = map[string]bool{}
	for _, word := range words {
		var tmp string
		for _, cc := range word {
			tmp += arr[cc-'a']
		}
		mm[tmp] = true
	}
	return len(mm)
}

// 812 O(n2)的凸包不会不想看
func triangleArea(x1, y1, x2, y2, x3, y3 int) float64 {
	return math.Abs(float64(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2)) / 2
}

func largestTriangleArea(points [][]int) (ans float64) {
	for i, p := range points {
		for j, q := range points[:i] {
			for _, r := range points[:j] {
				ans = math.Max(ans, triangleArea(p[0], p[1], q[0], q[1], r[0], r[1]))
			}
		}
	}
	return
}

// 800
func similarRGB(color string) string {
	var ans = "#"
	for i := 1; i < len(color); i += 2 {
		tmpNum := getInt(color[i])
		tmpMin := math.MaxUint8
		var currJ int
		getJ := func() int {
			if tmpNum == 0 {
				return 0
			} else {
				return tmpNum - 1
			}
		}
		for j := getJ(); j <= tmpNum+1; j++ {
			if j < 0 || j > 15 {
				continue
			}
			curr := (j-tmpNum)*16 + j - getInt(color[i+1])
			if curr < 0 {
				curr = -curr
			}
			if curr < tmpMin {
				tmpMin = curr
				//fmt.Println(j, j*16+j, tmpNum*16+getInt(color[i+1]), curr, tmpMin)
				if j > 9 {
					currJ = j - 10 + 'a'
				} else {
					currJ = j + '0'
				}
			}
		}
		ans += fmt.Sprintf("%c%c", currJ, currJ)
	}
	return ans
}

func getInt(cc uint8) int {
	if cc <= '9' {
		return int(cc - '0')
	}
	return int(cc - 'a' + 10)
}

// 796
func rotateString(s string, goal string) bool {
	if len(s) != len(goal) {
		return false
	}
	ll := len(s)
	goal += goal
	for i := 0; i < ll; i++ {
		if goal[i:ll+i] == s {
			return true
		}
	}
	return false
}

// 760
func anagramMappings(nums1 []int, nums2 []int) []int {
	mm := map[int][]int{}
	for index, num := range nums2 {
		mm[num] = append(mm[num], index)
	}
	var ans = make([]int, len(nums1))
	for i := 0; i < len(nums1); i++ {
		ans[i] = mm[nums1[i]][0]
		mm[nums1[i]] = mm[nums1[i]][1:]
	}
	return ans
}

// 697
type entry struct{ cnt, l, r int }

func findShortestSubArray(nums []int) (ans int) {
	mp := map[int]entry{}
	for i, v := range nums {
		if e, has := mp[v]; has {
			e.cnt++
			e.r = i
			mp[v] = e
		} else {
			mp[v] = entry{1, i, i}
		}
	}
	maxCnt := 0
	for _, e := range mp {
		if e.cnt > maxCnt {
			maxCnt, ans = e.cnt, e.r-e.l+1
		} else if e.cnt == maxCnt {
			ans = min(ans, e.r-e.l+1)
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

// 696
func countBinarySubstrings(s string) int {
	cz, co, ans := 0, 0, 0
	currZero := false
	for _, cc := range s {
		if cc == '0' {
			if currZero {
				cz++
			} else {
				currZero = true
				cz = 1
			}
			if cz <= co {
				ans++
			}
		} else {
			if !currZero {
				co++
			} else {
				currZero = false
				co = 1
			}
			if co <= cz {
				ans++
			}
		}
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
