package xin.jiuzhiqian.study.leetcode_history;

/**
 * @author : zhou
 */
public class Day3Solution {
    public static void main(String[] args) {
        Day3Solution solution = new Day3Solution();
        String s = "a dsfds ";
        System.out.println(solution.lengthOfLastWord(s));
    }

    public int lengthOfLastWord(String s) {
        int len = s.length();
        int lastIndex = len, firstIndex = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (firstIndex != 0) {
                    return lastIndex - firstIndex;
                } else {
                    lastIndex = i;
                }
            } else {
                firstIndex = i;
            }
        }
        return lastIndex - firstIndex;
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 垃圾题目
     *
     * @param n ll
     * @return String
     */
    public String countAndSay(int n) {
        return "" + n;
    }
}