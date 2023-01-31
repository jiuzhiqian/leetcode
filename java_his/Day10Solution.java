package xin.jiuzhiqian.study.leetcode_history;

import java.util.*;

/**
 * @author : zhou
 */
public class Day10Solution {
    public static void main(String[] args) {
        Day10Solution solution = new Day10Solution();
//        int[] nums = {1, -2147483648, 2};
//        String a = "0", b = "0";
//        int[] g = {1, 2, 3}, s = {1, 1};
        for (int i = 0; i < 10; i++) {
            i++;
            System.out.println(i);
        }
        String s = "abcabc";
        System.out.println(solution.repeatedSubstringPattern(s));
    }

    // 459 kmp todo
    public boolean repeatedSubstringPattern(String s) {
        return kmp(s + s, s);
    }

    private boolean kmp(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; i++) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for (int i = 1; i < n - 1; i++) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                match++;
                if (match == m - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // 455
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0, sIndex = 0, count = 0;
        while (gIndex < g.length && sIndex < s.length) {
            while (sIndex < s.length - 1 && g[gIndex] > s[sIndex]) {
                sIndex++;
            }
            if (s[sIndex++] >= g[gIndex++]) {
                count++;
            }
        }
        return count;
    }

    // 453
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int step = 0;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        for (int num : nums) {
            step += (num - min);
        }
        return step;
    }

    // 448
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = Math.abs(nums[i]);
            if (nums[tmp - 1] > 0) {
                nums[tmp - 1] = -nums[tmp - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    // 441 Gaussian GG
    public int arrangeCoins(int n) {
        int left = 1, right = n, mid;
        long sum;
        while (left < right) {
            mid = left + (right - left) / 2;
            sum = mid * (mid + 1) / 2;
            if (sum == n) {
                return mid;
            } else if (sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
        /*int left = 1, right = n, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if ((mid + 1) == n / mid * 2) {
                return mid;
            } else if ((mid + 1) > n / mid * 2) {
                if (right - left == 1) {
                    return left;
                }
                right = mid - 1;
            } else {
                if (right - left == 1) {
                    return left;
                }
                left = mid + 1;
            }
        }
        return left;*/
    }

    // 434
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        return count;

        /*String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;*/
    }

    // 415
    public String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        int tmp;
        boolean addOne = false;
        StringBuilder stringBuilder = new StringBuilder();
        while ((n1 >= 0 && n2 >= 0) || addOne) {
            tmp = 0;
            if (n1 >= 0) {
                tmp += num1.charAt(n1--) - '0';
            }
            if (n2 >= 0) {
                tmp += num2.charAt(n2--) - '0';
            }
            if (addOne) {
                tmp++;
            }
            if (tmp >= 10) {
                stringBuilder.append(tmp - 10);
                addOne = true;
            } else {
                addOne = false;
                stringBuilder.append(tmp);
            }
        }
        return stringBuilder.reverse().toString();
    }

    // 414
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (second < num && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }
        if (third > Long.MIN_VALUE) {
            return (int) third;
        }
        return (int) first;
    }

    // 409
    public int longestPalindrome(String s) {
        int[] nums = new int[52];
        int len = 0;
        boolean andOne = false;
        for (int i = 0; i < s.length(); i++) {
            int key = s.charAt(i) - 'A';
            if (key > 25) {
                key -= 6;
            }
            nums[key]++;
        }
        for (int num : nums) {
            len += (num / 2) * 2;
            if (num % 2 == 1) {
                andOne = true;
            }
        }
        if (andOne) {
            len++;
        }
        return len;

        // lot mem
        /*Set<Character> set = new HashSet<>();
        int len = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                len += 2;
            } else {
                set.add(c);
            }
        }
        if (!set.isEmpty()) {
            len++;
        }
        return len;*/
    }
}