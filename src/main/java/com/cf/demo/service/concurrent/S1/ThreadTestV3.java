package com.cf.demo.service.concurrent.S1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9,....,15]
 *
 * 要求遍历数组，遇到可以同时被3和5整除的数字，打印C；遇到仅能被5整除的数字，打印B；遇到仅能被3整除的数字，打印A；其他打印数字本身；
 *
 * 要求四个线程，每一个线程执行一个打印方法，数组只遍历一次。
 *
 * 预期结果：
 * C
 * B B B
 * A A A A A
 */
public class ThreadTestV3 implements Runnable {

    private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private final static ReentrantLock reentrantLock = new ReentrantLock();

    private final static Condition condition = reentrantLock.newCondition();

    private static volatile Integer index = 0;

    private String type;

    public ThreadTestV3(String t) {
        type = t;
    }


    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {

                String flag = "";

                while (index < array.length && !(flag = checkFlag(array[index])).equals(type)) {
                    System.out.println(Thread.currentThread() + " await current index " + index);
                    condition.await();
                }

                if (index == array.length) {
                    return;
                }

                if (type.equals("D")) {
                    System.out.println(Thread.currentThread() + " " + array[index]);
                } else {
                    System.out.println(Thread.currentThread() + " " + flag);
                }
                index++;
                System.out.println(Thread.currentThread() + " signal all ");

                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadTestV3("A"));
        thread1.start();

        Thread thread2 = new Thread(new ThreadTestV3("B"));
        thread2.start();

        Thread thread3 = new Thread(new ThreadTestV3("C"));
        thread3.start();

        Thread thread4 = new Thread(new ThreadTestV3("D"));
        thread4.start();


        // Q: join 到底是什么含义？？

        /* 当在当前线程 调用 线程B的join方法时，当前线程阻塞等待 直到线程B执行完毕才能继续执行 */
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
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
