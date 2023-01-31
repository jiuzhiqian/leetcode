package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;

import java.util.Arrays;

/**
 * @author : zhou
 */
public class Day6Solution {
    public static void main(String[] args) {
        Day6Solution solution = new Day6Solution();
        int[] nums = {1, 3, 5, 6, 7, 9};
        solution.rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }

    // 198
    public int rob(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int p1 = 0, p2 = 0, total = 0;
        for (int i = 0; i < nums.length; i++) {
            total = Math.max(p2, p1 + nums[i]);
            p1 = p2;
            p2 = total;
        }
        return total;
    }

    // 189,双重反转
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 172
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    // 171
    public int titleToNumber(String s) {
        int res = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            res += ((int) s.charAt(i) + 1 - 'A') * Math.pow(26, len - i - 1);
        }
        return res;
        /*int res = 0;
        int len = s.length();
        if (s.isEmpty()) {
            return res;
        }
        int charA = 'A';
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int charC = (int) c + 1 - charA;
            res += charC * Math.pow(26, len - i - 1);
        }
        return res;*/
    }

    // 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}