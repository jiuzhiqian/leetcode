package xin.jiuzhiqian.study.leetcode_history;

import xin.jiuzhiqian.study.leetcode_history.ListNode;
import xin.jiuzhiqian.study.leetcode_history.TreeNode;

import java.util.*;

/**
 * @author : zhou
 */
public class Day4Solution {
    public static void main(String[] args) {
        Day4Solution solution = new Day4Solution();
//        int[] digits = {9, 9};
//        System.out.println(Arrays.toString(solution.plusOne(digits)));
//        String a = "1010", b = "1011";
//        int a = 7;
//        System.out.println(solution.climbStairs(a));
//        ListNode l1 = new ListNode(1, new ListNode(1, new ListNode(1,new ListNode(1))));
        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(3,
                        new TreeNode(4), null)),
                new TreeNode(2, new TreeNode(3), new TreeNode(2)));
        System.out.println(solution.levelOrderBottom(treeNode));
    }

    // 107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode tt = queue.poll();
                tmp.add(tt.val);
                if (tt.left != null) {
                    queue.offer(tt.left);
                }
                if (tt.right != null) {
                    queue.offer(tt.right);
                }
                len--;
            }
            res.add(0, tmp);
        }
        return res;
    }

    // 104
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getStrength(root.left, root.right, 1);
    }

    private int getStrength(TreeNode left, TreeNode right, Integer level) {
        if (left == null && right == null) {
            return level;
        }
        int lenL = 0, lenR = 0;
        level++;
        if (left != null) {
            lenL = getStrength(left.left, left.right, level);
        }
        if (right != null) {
            lenR = getStrength(right.left, right.right, level);
        }
        return Math.max(lenL, lenR);
    }

    // 101
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    // 100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        } else {
            return false;
        }
    }

    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head;
        while (next.next != null) {
            if (next.val == next.next.val) {
                next.next = next.next.next;
            } else {
                next = next.next;
            }
        }
        return head;
    }

    //70
    public int climbStairs(int n) {
        if (n < 2) return n;
        int r1 = 1, r2 = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = r1 + r2;
            r1 = r2;
            r2 = res;
        }
        return res;
    }

    //69
    public int mySqrt(int x) {
        if (x < 2) return x;
        int mid = x / 2, left = 0, right = x;
        while (left < right) {
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
        }
        return left - 1;
    }

    // 67
    public String addBinary(String a, String b) {
        int lenA = a.length() - 1, lenB = b.length() - 1, toSum = 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        while (lenA >= 0 || lenB >= 0) {
            res = (lenA >= 0 ?
                    Integer.parseInt(String.valueOf(a.charAt(lenA--))) : 0)
                    + (lenB >= 0 ?
                    Integer.parseInt(String.valueOf(b.charAt(lenB--))) : 0) + toSum;
            stack.push(res % 2);
            toSum = res / 2;
        }
        if (toSum != 0) {
            stack.push(toSum);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    // 66
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i > 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        if (digits[0] == 9) {
            digits[0] = 0;
            int[] res = new int[len + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, len);
            return res;
        } else {
            digits[0]++;
            return digits;
        }
    }
}