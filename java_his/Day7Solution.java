package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : zhou
 */
public class Day7Solution {
    public static void main(String[] args) {
        Day7Solution solution = new Day7Solution();
//        int n = 19;
        String s = "ab", t = "aa";
        System.out.println(solution.isIsomorphic(s, t));
    }

    // 231 位运算
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    // 219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    // 217
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return true;
            }
            hashMap.put(num, 0);
        }
        return false;
        /*Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;*/
    }

    // 206
    public ListNode reverseList(ListNode head) {
        ListNode res = null;
        ListNode curr = head;
        while (curr != null) {
            res = new ListNode(curr.val, res);
            curr = curr.next;
        }
        return res;
    }

    // 205
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else if (map.containsValue(t.charAt(i))) {
                return false;
            } else {
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    // 204
    public int countPrimes(int n) {
        return n;
    }

    // 203
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0, head);
        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }

    // 202 方法二,快慢指针找循环,节省内存
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            int tmp = 0;
            while (n > 0) {
                tmp += (n % 10) * (n % 10);
                n /= 10;
            }
            if (set.contains(tmp)) {
                return false;
            }
            n = tmp;
            set.add(n);
        }
        return true;
    }
}