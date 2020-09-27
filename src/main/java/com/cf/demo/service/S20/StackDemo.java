package com.cf.demo.service.S20;

/**
 * @author liweinan
 * @date 2020/8/13
 */
public class StackDemo {
    int[] array;
    int size;
    int tail;
    /** initialize your data structure here. */
    public StackDemo(int size) {
        array = new int[size];
        this.size = size;
    }

    public void push(int x) {
        if (tail == size ) {
            System.out.println("stack is full:" + size);
            return;
        }
        array[tail++] = x;
    }

    public int pop() {
        if (array.length == 0) {
            return -1;
        }
        return array[--tail];
    }

    public int top() {
        if (array.length == 0) {
            return -1;
        }
        return array[tail - 1];
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        StackDemo stackDemo = new StackDemo(10);
        stackDemo.push(1);
        stackDemo.push(2);
        stackDemo.push(3);
        int pop = stackDemo.pop();

    }
}

