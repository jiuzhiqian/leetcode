package xin.jiuzhiqian.study.leetcode_history;

import org.springframework.data.relational.core.sql.In;

import java.util.Stack;

/**
 * @author : feng
 */
class MyQueue {
    Stack<Integer> inputStack;
    Stack<Integer> outputStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inputStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}