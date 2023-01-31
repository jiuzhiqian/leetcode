package xin.jiuzhiqian.study.leetcode_history;

import java.util.List;

/**
 * @author : feng
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val2) {
        val = val2;
    }

    public Node(int val2, List<Node> children2) {
        val = val2;
        children = children2;
    }
};