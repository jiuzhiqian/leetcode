package xin.jiuzhiqian.study.leetcode_history;


import java.util.Arrays;

/**
 * @author : feng
 */
public class Day28Solution {

    public static void main(String[] args) {
        Day28Solution solution = new Day28Solution();
        int[] nums = {2, 1};
        // System.out.println(solution.check(nums));
        System.out.println(solution.mergeAlternately("abcd", "pd"));
    }

    // 1768
    public String mergeAlternately(String word1, String word2) {
        int len = Math.min(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(word1.charAt(i)).append(word2.charAt(i));
        }
        sb.append(word1.substring(len)).append(word2.substring(len));
        return sb.toString();
    }

    // 1779
    public int nearestValidPoint(int x, int y, int[][] points) {
        int min = Integer.MAX_VALUE, ans = -1;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x) {
                if (Math.abs(points[i][1] - y) < min) {
                    ans = i;
                    min = Math.abs(points[i][1] - y);
                }
            } else if (points[i][1] == y) {
                if (Math.abs(points[i][0] - x) < min) {
                    ans = i;
                    min = Math.abs(points[i][0] - x);
                }
            }
        }
        return ans;
    }

    // 1758
    public int minOperations(String s) {
        int a = 0, b = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == s.charAt(i) - '0') {
                a++;
            } else {
                b++;
            }
        }
        return Math.min(a, b);

        /*// a双0,b双1,c单0,d单1
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '0') {
                    a++;
                } else {
                    b++;
                }
            } else {
                if (s.charAt(i) == '0') {
                    c++;
                } else {
                    d++;
                }
            }
        }
        return Math.min(b + c, a + d);*/
    }

    // 1752
    public boolean check(int[] nums) {
        int c = 0;
        if (nums.length < 2) {
            return true;
        }
        if (nums[0] < nums[nums.length - 1]) {
            c++;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                c++;
            }
        }
        return c <= 1;
    }

    // 1763 todo
    public String longestNiceSubstring(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int t = s.charAt(i);
            if (t >= 'a') {
                arr[t - 'a']++;
            } else {
                arr[t - 'A'] += 10000;
            }
        }
        boolean cc = true;
        for (int num : arr) {
            if (num != 0 && (num < 1000 || num % 10000 == 0)) {
                cc = false;
                break;
            }
        }
        if (cc) {
            return s;
        }

        StringBuilder pre = new StringBuilder(), curr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (check(arr, s.charAt(i))) {
                curr.append(s.charAt(i));
            } else {
                String ss = longestNiceSubstring(curr.toString());
                if (ss.length() == curr.length()) {
                    if (curr.length() > pre.length()) {
                        pre = curr;
                    }
                } else {
                    ss = longestNiceSubstring(ss);
                }
                curr = new StringBuilder();
            }
        }
        if (curr.length() > pre.length()) {
            pre = curr;
        }
        return pre.toString();
    }

    private boolean check(int[] arr, int t) {
        int index;
        if (t >= 'a') {
            index = t - 'a';
        } else {
            index = t - 'A';
        }
        if (arr[index] % 10000 == 0 || arr[index] < 1000) {
            return false;
        }
        return true;
    }

    // 1736 简单粗暴
    public String maximumTime(String time) {
        int first;
        StringBuilder sb = new StringBuilder();
        if (time.charAt(0) == '?') {
            if (time.charAt(1) == '?' || time.charAt(1) < '4') {
                first = 2;
            } else {
                first = 1;
            }
        } else {
            first = time.charAt(0) - '0';
        }
        sb.append(first);
        if (time.charAt(1) == '?') {
            if (first == 2) {
                sb.append('3');
            } else {
                sb.append('9');
            }
        } else {
            sb.append(time.charAt(1));
        }
        sb.append(':');
        if (time.charAt(3) == '?') {
            sb.append('5');
        } else {
            sb.append(time.charAt(3));
        }
        if (time.charAt(4) == '?') {
            sb.append('9');
        } else {
            sb.append(time.charAt(4));
        }
        return sb.toString();
    }

    // 1748
    public int sumOfUnique(int[] nums) {
        int[] buckets = new int[101];
        for (int n : nums) {
            buckets[n]++;
        }
        int total = 0;
        for (int i = 1; i < buckets.length; i++) {
            total += (buckets[i] == 1 ? i : 0);
        }
        return total;
    }

    // 1742
    public int countBalls(int lowLimit, int highLimit) {
        int[] dic = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            int t1 = i, t2 = 0;
            while (t1 > 0) {
                t2 += (t1 % 10);
                t1 /= 10;
            }
            dic[t2]++;
        }
        int max = dic[0];
        for (int n : dic) {
            max = Math.max(max, n);
        }
        return max;
    }

    // 1725
    public int countGoodRectangles(int[][] rectangles) {
        int count = 1, max = Math.min(rectangles[0][0], rectangles[0][1]);
        for (int i = 1; i < rectangles.length; i++) {
            int min = Math.min(rectangles[i][0], rectangles[i][1]);
            if (max == min) {
                count++;
            } else if (min > max) {
                max = min;
                count = 1;
            }
        }
        return count;
    }

    // 1732
    public int largestAltitude(int[] gain) {
        if (gain.length < 1) {
            return 0;
        }
        int max = gain[0], curr = gain[0];
        for (int i = 1; i < gain.length; i++) {
            curr += gain[i];
            max = Math.max(curr, max);
        }
        return Math.max(max, 0);
    }

    // 1710
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int base = 10000;
        int[] nums = new int[boxTypes.length];
        for (int i = 0; i < boxTypes.length; i++) {
            nums[i] = boxTypes[i][1] * base + boxTypes[i][0];
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] % base >= truckSize) {
                ans += truckSize * (nums[i] / base);
                break;
            } else {
                ans += (nums[i] / base) * (nums[i] % base);
                truckSize -= nums[i] % base;
            }
        }
        return ans;
    }

    // 1716
    public int totalMoney(int n) {
        int total = 0;
        int t1 = n % 7, t2 = n / 7;
        if (t1 > 0) {
            total += ((t1 + 1 + 2 * t2) * t1 / 2);
        }
        if (t2 > 0) {
            total += 28 * t2 + 7 * t2 * (t2 - 1) / 2;
        }
        return total;
    }

    // 1704
    public boolean halvesAreAlike(String s) {
        int ch = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i < len / 2) {
                if (getCharNum(Character.toLowerCase(s.charAt(i)))) {
                    ch++;
                }
            } else {
                if (getCharNum(Character.toLowerCase(s.charAt(i)))) {
                    ch--;
                }
            }
        }
        return ch == 0;
    }

    private boolean getCharNum(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
}