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
public class ThreadTest_BUG implements Runnable {

    private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};


    ReentrantLock reentrantLock = new ReentrantLock();

    Condition condition = reentrantLock.newCondition();

    private static volatile Integer index = 0;

    private String type;
    public ThreadTest_BUG(String t) {
        type = t;
    }


    @Override

    public void run() {
        while (true) {
            reentrantLock.lock();
            try {
                String flag = checkFlag(array[index]);
                /* bug1: 正常执行的线程，才能将index加1 */
                index++;
                if (index == array.length) {
                    condition.signal();
                    break;
                }

                if (flag.equals(type)) {
                    if (type.equals("D")) {
                        System.out.println(Thread.currentThread() + " " + flag);
                    } else {
                        System.out.println(Thread.currentThread() + " " + array[index]);
                    }
                    condition.signalAll();
                } else {
                    System.out.println(Thread.currentThread() + " await current index " + index);
                    condition.await();
                }
                /* Q: 这种方式，T1、T2 同时竞争锁
                      T1 率先拿到锁后，进入if分支，执行 signalAll，然后释放锁
                      T2 再拿到锁，进入else分支，执行await，于是就死等在这里了 -||-
                */


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadTest_BUG("A"));
        thread1.start();

        Thread thread2 = new Thread(new ThreadTest_BUG("B"));
        thread2.start();

        Thread thread3 = new Thread(new ThreadTest_BUG("C"));
        thread3.start();

        Thread thread4 = new Thread(new ThreadTest_BUG("D"));
        thread4.start();


        // Q: join 到底是什么含义？？
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


    /*
     * 反思：
     * 卡住的地方：1、全局的索引 index，在class 内部无法操作。原因是没有在构造器中传入，从内部类直接饮用
     *
     *           2、join 不熟练，不知道其用法。
     *
     *           3、await 用的方式不对，导致死等
     * */
}
