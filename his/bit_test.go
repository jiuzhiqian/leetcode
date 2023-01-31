package leet

import (
	"fmt"
	"math"
	"math/bits"
	"sort"
	"testing"
)

func TestLeetCodeBit(t *testing.T) {
	//res := divide(7, -3)
	//res := subset([]int{1, 2})
	//res := singleNumber([]int{4, 1, 2, 1, 2, 1, 2})
	//res := findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")
	//res := reverseBits(43261596)
	//res := hammingWeight(11)
	//res := rangeBitwiseAnd(1, 1)
	//res := singleNumber3([]int{1, 2, 1, 6, 2, 4})
	//res := canPermutePalindrome("abb")
	//res := findDuplicate2([]int{1, 3, 3, 2, 4, 5, 6, 7, 8})
	//res := maxProduct([]string{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"})
	//res := countBits(1111111)
	//res := validUtf8([]int{145})
	//res := validUtf8([]int{237})
	//fmt.Println(237 >> 7 & 1)
	//res := integerReplacement(7)
	//res := readBinaryWatch(1)
	//res := findComplement(5)
	//res := totalHammingDistance([]int{4, 14, 4})
	//res := countBits(3)
	res := makesquare([]int{1, 1, 2, 2, 2})
	t.Log(res)
}

// 473
func makesquare(matchsticks []int) bool {
	var total int
	ll := len(matchsticks)
	for _, num := range matchsticks {
		total += num
	}
	if total%4 != 0 {
		return false
	}
	var useArr = make([]bool, ll)
	sideCnt := 4
	sideLen := total >> 2
	sort.Ints(matchsticks)
	for i := ll - 1; i >= 0; i-- {
		if matchsticks[i] == sideLen {
			useArr[i] = true
			sideCnt--
			continue
		}
		for j := 0; j < i-1; j++ {
			if useArr[j] {
				continue
			}
			if matchsticks[i]+matchsticks[j] == sideLen {
				sideCnt--
				useArr[i], useArr[j] = true, true
			}
		}
	}
	return true
}

// 338
func countBits(n int) []int {
	var ans = make([]int, n+1)
	for i := 0; i <= n; i++ {
		ans[i] = bits.OnesCount32(uint32(i))
	}
	return ans
}

// 477
func totalHammingDistance(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < 30; i++ {
		c := 0
		for _, val := range nums {
			c += val >> i & 1
		}
		ans += c * (n - c)
	}
	return
}

// 461
func hammingDistance(x int, y int) int {
	//if x < y {
	//	return hammingDistance(y, x)
	//}
	//tmpArr := [2]int{x, y}
	//if calcMap[tmpArr] {
	//	fmt.Println(tmpArr)
	//	return 0
	//}
	//calcMap[tmpArr] = true

	var ans int
	for i := 31; i >= 0; i-- {
		if x>>i&1 != y>>i&1 {
			ans++
		}
	}
	return ans
}

// 476
func findComplement(num int) int {
	var ans int
	begin := false
	for i := 31; i >= 0; i-- {
		if !begin && num>>i&1 == 1 {
			begin = true
		}
		if !begin {
			continue
		}
		ans = ans << 1
		ans |= 1 - num>>i&1
	}
	return ans
}

// 401
func readBinaryWatch(turnedOn int) (ans []string) {
	for i := 0; i < 1<<10; i++ {
		h, m := i>>6, i&(1<<6-1) // 用位运算取出高 4 位和低 6 位
		if h > 11 || m > 59 {
			continue
		}
		if bits.OnesCount(uint(i)) == turnedOn {
			ans = append(ans, fmt.Sprintf("%d:%02d", h, m))
		}
	}
	return
}

// 397
func integerReplacement(n int) int {
	if n == 1 {
		return 0
	}
	if n&1 == 0 {
		return 1 + integerReplacement(n>>1)
	}
	t1 := 1 + integerReplacement(n+1)
	t2 := 1 + integerReplacement(n-1)
	if t1 < t2 {
		return t1
	}
	return t2
}

// 393
func validUtf8(data []int) bool {
	var tenLen int
	for _, num := range data {
		if tenLen > 0 {
			if num>>7&1 == 1 && num>>6&1 == 0 {
				tenLen--
			} else {
				//fmt.Println(tenLen, 111)
				return false
			}
		} else {
			if num>>7&1 == 0 {
				continue
			} else {
				if num>>6&1 != 1 {
					//fmt.Println(num, 666)
					return false
				}
				tenLen++
				for i := 1; i <= 3; i++ {
					if i == 3 && num>>3&1 == 1 {
						//fmt.Println(333)
						return false
					}
					if num>>(6-i)&1 == 0 {
						break
					}
					tenLen++
				}
			}
			//fmt.Println(num, tenLen)
		}
	}
	return tenLen == 0
}

// 338
func countBits2(n int) []int {
	bits := make([]int, n+1)
	highBit := 0
	for i := 1; i <= n; i++ {
		if i&(i-1) == 0 {
			highBit = i
		}
		bits[i] = bits[i-highBit] + 1
	}
	return bits
}

func maxProduct(words []string) (ans int) {
	masks := map[int]int{}
	for _, word := range words {
		mask := 0
		for _, ch := range word {
			mask |= 1 << (ch - 'a')
		}
		if len(word) > masks[mask] {
			masks[mask] = len(word)
		}
	}

	for x, lenX := range masks {
		for y, lenY := range masks {
			if x&y == 0 && lenX*lenY > ans {
				ans = lenX * lenY
			}
		}
	}
	return
}

// 318
func maxProduct2(words []string) int {
	var ans int
	ll := len(words)
	var arr = make([]int, ll)
	for key, word := range words {
		var tmp int
		for _, char := range word {
			tmp |= 1 << (int(char - 'a'))
		}
		arr[key] = tmp
	}
	for i := 0; i < ll; i++ {
		for j := 0; j < ll; j++ {
			if i == j || arr[i]&arr[j] > 0 || len(words[i])*len(words[j]) < ans {
				continue
			}
			ans = len(words[i]) * len(words[j])
		}
	}
	return ans
}

// 287 搞不懂，又可以投了
func findDuplicate2(nums []int) int {
	slow, fast := 0, 0
	for slow != fast {
		slow = nums[slow]
		fast = nums[nums[fast]]
	}
	slow = 0
	for slow != fast {
		slow = nums[slow]
		fast = nums[fast]
	}
	return slow
}

// 266
func canPermutePalindrome(s string) bool {
	var sum, sum2 int
	for _, char := range s {
		sum ^= 1 << int(char-'a')
	}
	isRemove := false
	for _, char := range s {
		if !isRemove && 1<<int(char-'a') == sum {
			isRemove = true
			continue
		}
		sum2 ^= 1 << int(char-'a')
	}
	return sum2 == 0

	// GG
	/*var sum, sum2 int
	for _, char := range s {
		sum ^= int(char)
	}
	if sum == 0 {
		return true
	}
	isRemove := false
	for _, char := range s {
		if !isRemove && int(char) == sum {
			isRemove = true
			continue
		}
		sum2 ^= int(char)
	}
	return sum2 == 0*/
}

// 260，两个单数
func singleNumber3(nums []int) []int {
	var sum int
	for _, num := range nums {
		sum ^= num
	}
	// 找出最低位的1，区分两单数的分组
	less := sum & -sum
	a1, a2 := 0, 0
	for _, num := range nums {
		if num&less == 0 {
			a1 ^= num
		} else {
			a2 ^= num
		}
	}
	return []int{a1, a2}
}

// 201
func rangeBitwiseAnd(m int, n int) int {
	var num int
	for m < n {
		m, n = m>>1, n>>1
		num++
	}
	return m << num
}

func hammingWeight(num uint32) (ones int) {
	for ; num > 0; num &= num - 1 {
		ones++
	}
	return
}

// 191
func hammingWeight2(num uint32) int {
	var ans int
	for i := 0; i < 32; i++ {
		ans += int(num >> i & 1)
	}
	return ans
}

const (
	m1 = 0x55555555 // 01010101010101010101010101010101
	m2 = 0x33333333 // 00110011001100110011001100110011
	m4 = 0x0f0f0f0f // 00001111000011110000111100001111
	m8 = 0x00ff00ff // 00000000111111110000000011111111
)

// 看不懂，投了吧
func reverseBits(n uint32) uint32 {
	n = n>>1&m1 | n&m1<<1
	n = n>>2&m2 | n&m2<<2
	n = n>>4&m4 | n&m4<<4
	n = n>>8&m8 | n&m8<<8
	return n>>16 | n<<16
}

// 190
func reverseBits2(num uint32) uint32 {
	var ans uint32
	ll := 32
	for i := 0; i < ll; i++ {
		ans = num>>i&1 + ans<<1
	}
	return ans
}

func findRepeatedDnaSequences(s string) (ans []string) {
	ll := len(s)
	if ll < 11 {
		return
	}
	var curr int
	bitMap := map[uint8]int{'A': 0, 'C': 1, 'G': 2, 'T': 3}
	currMap := map[int]int{}
	for i := 0; i < 9; i++ {
		curr = curr<<2 | bitMap[s[i]]
	}
	for i := 9; i < ll; i++ {
		curr = (curr<<2 | bitMap[s[i]]) & (1<<(2*10) - 1)
		currMap[curr]++
		if currMap[curr] == 2 {
			ans = append(ans, s[i-9:i+1])
		}
	}
	return
}

// 187
func findRepeatedDnaSequences2(s string) []string {
	var ans []string
	if len(s) <= 10 {
		return ans
	}
	mm := map[string]int{}
	for i := 9; i < len(s); i++ {
		mm[s[i-9:i+1]]++
	}
	for str, cnt := range mm {
		if cnt > 1 {
			ans = append(ans, str)
		}
	}
	return ans
}

// 137
func singleNumber(nums []int) int {
	// 依次确认每一个二进制数 32n O(n)
	var ans int32
	for i := 0; i < 32; i++ {
		var total int
		for _, num := range nums {
			total += num >> i & 1
		}
		if total%3 == 1 {
			ans += 1 << i
		}
	}
	return int(ans)

	/*freqMap := map[int]int{}
	var ans int
	for _, num := range nums {
		if freqMap[num] > 1 {
			continue
		}
		freqMap[num]++
		ans ^= num
	}
	return ans*/
}

// 136
func singleNumber2(nums []int) int {
	var ans int
	for _, num := range nums {
		ans ^= num
	}
	return ans
}

func subsetsWithDup(nums []int) (ans [][]int) {
	sort.Ints(nums)
	var t []int
	var dfs func(bool, int)
	dfs = func(choosePre bool, cur int) {
		if cur == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(false, cur+1)
		if !choosePre && cur > 0 && nums[cur-1] == nums[cur] {
			return
		}
		t = append(t, nums[cur])
		dfs(true, cur+1)
		t = t[:len(t)-1]
	}
	dfs(false, 0)
	return
}

// 90
func subsetsWithDup2(nums []int) (ans [][]int) {
	sort.Ints(nums)
	n := len(nums)
outer:
	for mask := 0; mask < 1<<n; mask++ {
		var t []int
		for i, v := range nums {
			if mask>>i&1 > 0 {
				if i > 0 && mask>>(i-1)&1 == 0 && v == nums[i-1] {
					continue outer
				}
				t = append(t, v)
			}
		}
		ans = append(ans, append([]int(nil), t...))
	}
	return
}

// 89 搞不懂
func grayCode(n int) []int {
	ans := make([]int, 1, 1<<n)
	for i := 1; i <= n; i++ {
		for j := len(ans) - 1; j >= 0; j-- {
			fmt.Println(i, j, ans[j], ans[j]|1<<(i-1))
			ans = append(ans, ans[j]|1<<(i-1))
		}
	}
	return ans
}

// 78
func subset(nums []int) (ans [][]int) {
	var set []int
	var dfs func(int)
	dfs = func(cur int) {
		if cur == len(nums) {
			ans = append(ans, append([]int(nil), set...))
			return
		}
		set = append(set, nums[cur])
		dfs(cur + 1)
		set = set[:len(set)-1]
		dfs(cur + 1)
	}
	dfs(0)
	return
}

// 29
func divide(dividend, divisor int) int {
	if dividend == 0 { // 考虑被除数为 0 的情况
		return 0
	}
	if divisor == 1 {
		return dividend
	} else if divisor == -1 {
		if dividend == math.MinInt32 {
			return math.MaxInt32
		} else {
			return -dividend
		}
	}
	rev := false
	if dividend > 0 {
		dividend = -dividend
		rev = !rev
	}
	if divisor > 0 {
		divisor = -divisor
		rev = !rev
	}
	if divisor < dividend {
		return 0
	}

	var arr []int
	for divisor >= dividend {
		arr = append(arr, divisor)
		divisor += divisor
	}
	var ans int
	ll := len(arr)
	for i := ll - 1; i >= 0; i-- {
		if dividend <= arr[i] {
			ans += int(math.Pow(2.0, float64(i)))
			dividend -= arr[i]
		}
	}
	if rev {
		return -ans
	}
	return ans
}
