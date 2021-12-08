package com.cf.demo.service.concurrent.S1;


import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9,....,15]
 *
 * 要求遍历数组，遇到仅能被3整除的数字，打印A；遇到仅能被5整除的数字，打印B；遇到可以同时被3和5整除的数字，打印C；其他打印数字本身；
 *
 * 要求四个线程，每一个线程执行一个打印方法，数组只遍历一次。
 *
 *
 * 练手用
 */
public class ThreadTestV4 {

    private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};


    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    static AtomicInteger count = new AtomicInteger();

    static class MyThread implements Runnable {

        private String t;
        public MyThread(String type) {
            t = type;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {

                lock.lock();
                try {
                    while (!t.equals(checkFlag(count.get()))) {
                        condition.await();
                    }

                    System.out.println(Thread.currentThread() +  t + " " + count.get());

                    count.getAndIncrement();

                    if (count.get() >= 100) {
                        break;
                    }
                    condition.signalAll();
                }finally {
                    lock.unlock();
                }


            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyThread("A")).start();
        new Thread(new MyThread("B")).start();
        new Thread(new MyThread("C")).start();
        new Thread(new MyThread("D")).start();
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
