package com.cf.demo.service.concurrent.S1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9,....,15]
 * <p>
 * 要求遍历数组，遇到仅能被3整除的数字，打印A；遇到仅能被5整除的数字，打印B；遇到可以同时被3和5整除的数字，打印C；其他打印数字本身；
 * <p>
 * 要求四个线程，每一个线程执行一个打印方法，数组只遍历一次。
 * <p>
 *
 * 练手用
 */
public class ThreadTestV4 {

    private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};


    public static void main(String[] args) throws InterruptedException {

    }


    private static String checkFlag(int n) {
        if (n % 15 == 0) {
            return "C";
        } else if (n % 5 == 0) {
            return "B";
        } else if (n % 3 == 0) {
            return "A";
        } else {
            return "D";
        }
    }
}
