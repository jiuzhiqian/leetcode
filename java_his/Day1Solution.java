package xin.jiuzhiqian.study.leetcode_history;

import java.util.Arrays;

/**
 * @author : zhou
 */
public class Day1Solution {
    public static void main(String[] args) {
        Day1Solution solution = new Day1Solution();
        /*int[] num1 = new int[2];
        int[] num2 = {2};
        int n2 = num2.length;
        solution.merge(num1, 0, num2, 1);*/
//        String s = "safdsaxasdewdc";
//        solution.longestPalindrome(s);
//        int x = 121;
        String[] strs = {"aa", "a"};
        System.out.println(solution.longestCommonPrefix(strs));
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String baseString = strs[0];
        int maxLen = baseString.length();
        int len = 0;
        boolean isEnd = false;
        for (int i = 0; i < maxLen; i++) {
            for (String str : strs) {
                if (str.length() <= i || str.charAt(i) != baseString.charAt(i)) {
                    isEnd = true;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
            len = i + 1;
        }
        return baseString.substring(0, len);
    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }


    private void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0) {
            nums1[m + n - 1] = (nums1[m - 1] <= nums2[n - 1]) ? nums2[--n] : nums1[--m];
        }
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int t = 0;
        int y = x;
        while (x != 0) {
            t = t * 10 + x % 10;
            x /= 10;
        }
        if (t != y) {
            return false;
        }
        return true;
    }


    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) res;
    }

    public int romanToInt(String s) {
        int len = s.length();
        int tmp = 0, res = 0;
        for (int i = len - 1; i >= 0; i--) {
            int a = getValue(s.charAt(i));
            if (a >= tmp) {
                res += a;
                tmp = a;
            } else {
                res -= a;
            }
        }
        return res;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}