package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;
import xin.jiuzhiqian.study.leetcode_history.TreeNode;

import java.util.*;

/**
 * @author : zhou
 */
public class Day5Solution {
    public static void main(String[] args) {
        Day5Solution solution = new Day5Solution();
//        TreeNode root = new TreeNode(5, new TreeNode(3, new TreeNode(1), new TreeNode(4)), new TreeNode(6));
//        TreeNode root = new TreeNode();
//        int[] prices = new int[]{1, 5, 78, 3, 5, 7};
//        int[] prices = {1, 5, 3, 7};
//        String s = "A man, a plan, a canal: Panama";
        ListNode listNode = new ListNode(1, new ListNode(5, new ListNode(7, new ListNode(5))));
        System.out.println(solution.hasCycle(listNode));
    }

    // 141
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode next = head.next;
        while (next != null && next.next != null) {
            if (head == next) {
                return true;
            }
            head = head.next;
            next = next.next.next;
        }
        return false;
    }

    // 136
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    // 125
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // 122
    public int maxProfit2(int[] prices) {
        if (prices.length < 1) {
            return 0;
        }
        int last = prices[0], res = 0;
        for (int price : prices) {
            if (price > last) {
                res += (price - last);
            }
            last = price;
        }
        return res;
    }

    // 121
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            max = Math.max(max, (price - min));
        }
        return max;
    }

    // 119
    public List<Integer> getRow(int rowIndex) {
        List<Integer> before = new LinkedList<>();
        // 傻逼题,非得要rowIndex+1
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> res2 = new LinkedList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    res2.add(1);
                } else {
                    res2.add(before.get(j - 1) + before.get(j));
                }
            }
            if (i == rowIndex) {
                return res2;
            }
            before = res2;
        }
        return null;
    }

    // 118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmp = new LinkedList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    tmp.add(1);
                } else {
                    tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tmp);
        }
        return res;
    }

    // 112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int tmp = sum - root.val;
        boolean leftRes = false;
        boolean rightRes = false;
        if (root.left != null) {
            leftRes = hasPathSum(root.left, tmp);
        } else if (root.right == null && tmp == 0) {
            leftRes = true;
        }
        if (root.right != null) {
            rightRes = hasPathSum(root.right, tmp);
        } else if (root.left == null && tmp == 0) {
            rightRes = true;
        }
        return leftRes || rightRes;
    }

    // 先不管树

    // 108
    public TreeNode sortedArrayToBst(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        } else if (len == 1) {
            return new TreeNode(nums[0]);
        } else if (len == 2) {
            return new TreeNode(nums[1], new TreeNode(nums[0]), null);
        }
        int midIndex = len / 2;
        TreeNode res = new TreeNode(nums[midIndex]);
        int[] left = Arrays.copyOf(nums, midIndex);
        int[] right = new int[len - midIndex - 1];
        System.arraycopy(nums, midIndex + 1, right, 0, len - midIndex - 1);
        res.left = sortedArrayToBst(left);
        res.right = sortedArrayToBst(right);
        return res;
    }

    // 110
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int dd = Math.abs(getDep(root.left) - getDep(root.right));
        return dd <= 1;
    }

    private int getDep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDep(node.left), getDep(node.right)) + 1;
    }
}