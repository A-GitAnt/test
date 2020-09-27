package com.cf.demo.service.S20;

import java.util.Stack;

/**
 * @author liweinan
 * @date 2020/8/13
 */
public class S155 {
    Stack<Integer> normalStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public S155(int size) {
    }

    public void push(int x) {
        normalStack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = normalStack.pop();
        if (!minStack.isEmpty() && minStack.peek().equals(pop)) {
            minStack.pop();
        }
    }

    public int top() {
        if (!normalStack.isEmpty()) {
            return normalStack.peek();
        }
        return -1;
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return -1;
    }
}

