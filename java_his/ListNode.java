package xin.jiuzhiqian.study.leetcode_history;

/**
 * @author : zhou
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode tmp = this;
        StringBuilder res = new StringBuilder();
        while (tmp != null) {
            res.append(tmp.val);
            tmp = tmp.next;
        }
        return res.toString();
    }
}