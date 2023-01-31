package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author : zhou
 */
public class Day2Solution {
    public static void main(String[] args) {
        Day2Solution solution = new Day2Solution();
//        String s = "{dsa())}";
//        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));
//        ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(6)));
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(Arrays.toString(solution.removeElement(nums, 2)));
        int[] nums = {1, 3, 4, 6, 8, 9};
        System.out.println(solution.searchInsert(nums, 3));
    }


    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        if (target < nums[left]) {
            return 0;
        }
        if (target > nums[right]) {
            return right + 1;
        }
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (target <= nums[mid + 1]) {
                    return mid + 1;
                }
                left = mid + 1;
            } else {
                if (target == nums[mid - 1]) {
                    return mid-1;
                }else if(target>nums[mid-1]){
                    return mid;
                }
                right = mid - 1;
            }
        }
        return mid;
    }


    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        int lenN = needle.length();
        int lenH = haystack.length();
        for (int i = 0; i < lenH; i++) {
            for (int j = 0; j < lenN; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                } else if (i + lenN > lenH) {
                    return -1;
                }
                if (j == lenN - 1) {
                    return i;
                }
            }
        }
        return -1;
    }


    public int[] removeElement(int[] nums, int val) {
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return Arrays.copyOf(nums, j);
    }


    public int[] removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        int tmp = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != tmp) {
                tmp = nums[i];
                nums[++index] = nums[i];
            }
        }
        return Arrays.copyOfRange(nums, 0, index + 1);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 == null) ? l2 : l1;
        return res.next;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Character tmp = ' ';
        for (Character c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.empty() || stack.pop() != getCompare(c)) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    public Character getCompare(Character character) {
        switch (character) {
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                return ' ';
        }
    }
}