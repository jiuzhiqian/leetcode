package xin.jiuzhiqian.study.leetcode_history;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author : feng
 */
public class Day26Solution {

    public static void main(String[] args) {
        Day26Solution solution = new Day26Solution();
        int[] arr = {2,4,9,3};
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] logs = {"d1/", "d2/", "../", "d21/", "./"};
        System.out.println(Arrays.toString(solution.decrypt(arr, -2)));
        // System.out.println(solution.slowestKey(arr, "spuda"));
    }

    // 1652
    public int[] decrypt(int[] code, int k) {
        int[] ans = new int[code.length];
        if (k == 0) {
            return ans;
        }
        int begin = k > 0 ? 1 : code.length + k;
        k = Math.abs(k);
        int end = begin + k;
        for (int i = begin; i < end; i++) {
            ans[0] += code[i % code.length];
        }
        System.out.println(ans[0]);


        for (int i = 1; i < code.length; i++) {
            ans[i] = ans[i-1] - code[begin++%code.length] + code[end++ % code.length];
        }
        return ans;
    }

    // 1629
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int pre = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] > pre) {
                ans = keysPressed.charAt(i);
                pre = releaseTimes[i] - releaseTimes[i - 1];
            } else if (releaseTimes[i] - releaseTimes[i - 1] == pre && keysPressed.charAt(i) > ans) {
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }

    // 1624
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] min = new int[26];
        int[] max = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            min[index] = min[index] == 0 ? i + 1 : min[index];
            max[index] = i + 1;
        }
        int ans = -1;
        for (int i = 0; i < 26; i++) {
            if (min[i] != max[i] && min[i] != 0 && max[i] - min[i] > 0) {
                ans = Math.max(ans, max[i] - min[i] - 1);
            }
        }
        return ans;
    }

    // 1636
    public int[] frequencySort(int[] nums) {
        int[] base = new int[201];
        for (int num : nums) {
            base[num + 100]++;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 10000 * base[nums[i] + 100] - nums[i] + 100;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 100 - nums[i] % 10000;
        }
        return nums;
    }

    // 1614
    public int maxDepth(String s) {
        int count = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count--;
                max = Math.max(Math.abs(count), max);
            } else if (c == ')') {
                count++;
                max = Math.max(Math.abs(count), max);
            }
        }
        return count == 0 ? max : 0;
    }

    // 1608
    public int specialArray(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int x = len - i;
            if (nums[i] >= x && (i > 0 && nums[i - 1] < x || i == 0)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * 1598
     */
    public int minOperations(String[] logs) {
        int c = 0;
        for (String log : logs) {
            if ("../".equals(log)) {
                if (c > 0) {
                    c--;
                }
            } else if (!"./".equals(log)) {
                c++;
            }
        }
        return c;
    }

    /**
     * 1592 看起都脑壳痛
     * 执行耗时:1 ms,击败了96.99% 的Java用户
     * 内存消耗:36.5 MB,击败了94.98% 的Java用户
     */
    public String reorderSpaces(String text) {
        int spaceCount = 0, index = 0, step, wordCount = 0;
        boolean preIsChar = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
                preIsChar = false;
            } else {
                if (!preIsChar) {
                    wordCount++;
                    preIsChar = true;
                }
            }
        }
        preIsChar = false;
        StringBuilder sb = new StringBuilder();
        step = wordCount > 1 ? spaceCount / (wordCount - 1) : spaceCount;
        for (int i = 0; i < text.length(); i++) {
            if (preIsChar) {
                if (text.charAt(i) != ' ') {
                    sb.append(text.charAt(i));
                } else {
                    while (spaceCount > 0 && index++ < step) {
                        spaceCount--;
                        sb.append(' ');
                    }
                    index = 0;
                    preIsChar = false;
                }
            } else {
                if (text.charAt(i) != ' ') {
                    preIsChar = true;
                    sb.append(text.charAt(i));
                }
            }
        }
        while (spaceCount-- > 0) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // 1576
    public String modifyString(String s) {
        char[] cc = {'a', 'b', 'c'};
        char pre = '?';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '?') {
                sb.append(s.charAt(i));
                pre = s.charAt(i);
            } else {
                for (char c : cc) {
                    if (i > 0) {
                        if (c == pre) {
                            continue;
                        }
                    }
                    if (i < s.length() - 1) {
                        if (c == s.charAt(i + 1)) {
                            continue;
                        }
                    }
                    sb.append(c);
                    pre = c;
                    break;
                }
            }
        }
        return sb.toString();
    }

    // 1342
    public int numberOfSteps(int num) {
        int res = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                res++;
            } else if (num < 2) {
                res += 1;
            } else {
                res += 2;
            }
            num /= 2;
        }
        return res;
    }

    // 1588
    public int sumOddLengthSubArrays(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return arr[0] + arr[1];
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = arr[1];
        int sum = dp[0] + dp[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = arr[i] + (arr[i] + arr[i - 1]) * (i / 2) + dp[i - 2];
            sum += dp[i];
        }
        return sum;
    }

    // 1566
    public boolean containsPattern(int[] arr, int m, int k) {
        for (int i = 0; i <= arr.length - m * k; i++) {
            int offset = 0;
            for (offset = 0; offset < m * k; offset++) {
                if (arr[i + offset] != arr[i + offset % m]) {
                    break;
                }
            }
            if (offset == m * k) {
                return true;
            }
        }
        return false;
    }

    // 1560
    public List<Integer> mostVisited(int n, int[] rounds) {
        int len = rounds.length;
        List<Integer> ans = new ArrayList<>();
        if (rounds[len - 1] == rounds[0]) {
            ans.add(rounds[0]);
        } else if (rounds[len - 1] > rounds[0]) {
            for (int i = rounds[0]; i <= rounds[len - 1]; i++) {
                ans.add(i);
            }
        } else {
            for (int i = 0; i <= n; i++) {
                if (i <= rounds[len - 1] || i >= rounds[0]) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    // 1572
    public int diagonalSum(int[][] mat) {
        int count = 0, len = mat.length;
        if (len % 2 == 1) {
            count -= mat[len / 2][len / 2];
        }
        System.out.println(count);
        for (int i = 0; i < len; i++) {
            count += mat[i][i];
        }
        for (int i = 0; i < len; i++) {
            count += mat[len - 1 - i][i];
        }
        return count;
    }

    // 1544
    public String makeGood(String s) {
        int n = Math.abs('a' - 'A');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.add(s.charAt(i));
                continue;
            }
            if (Math.abs(stack.peek() - s.charAt(i)) == n) {
                stack.pop();
            } else {
                stack.add(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    // 1566
    public String thousandSeparator(int n) {
        int c = 0;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 10);
            n /= 10;
            if (++c % 3 == 0 && n != 0) {
                sb.append('.');
            }
        }
        return sb.reverse().toString();
    }

    // 1534
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}