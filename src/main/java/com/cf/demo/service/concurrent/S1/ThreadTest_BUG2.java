package com.cf.demo.service.concurrent.S1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9,....,15]
 * <p>
 * 要求遍历数组，遇到可以同时被3和5整除的数字，打印C；遇到仅能被5整除的数字，打印B；遇到仅能被3整除的数字，打印A；其他打印数字本身；
 * <p>
 * 要求四个线程，每一个线程执行一个打印方法，数组只遍历一次。
 * <p>
 * 预期结果：
 * C
 * B B B
 * A A A A A
 */
public class ThreadTest_BUG2 implements Runnable {

    private static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    ReentrantLock reentrantLock = new ReentrantLock();

    Condition condition = reentrantLock.newCondition();

    private static volatile Integer index = 0;

    private String type;

    public ThreadTest_BUG2(String t) {
        type = t;
    }


    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {

                String flag = checkFlag(array[index]);
                if (index == array.length) {
                    condition.signalAll();
                    break;
                }

                /* Bug: 这里虽然是从await状态放回，但是重新判断时取的flag还是老值！ */
                while (!flag.equals(type)) {
                    System.out.println(Thread.currentThread() + " await current index " + index);
                    condition.await();
                }

                if (type.equals("D")) {
                    System.out.println(Thread.currentThread() + " " + array[index]);
                } else {
                    System.out.println(Thread.currentThread() + " " + flag);
                }
                index++;
                System.out.println(Thread.currentThread() + " signal all ");

                condition.signalAll();
                /* Q: 这种方式，T1、T2 同时竞争锁
                      T1 率先拿到锁后执行 signalAll，然后释放锁
                      T2 再拿到锁，执行await，于是就死等在这里了 -||-
                */
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadTest_BUG2("A"));
        thread1.start();

        Thread thread2 = new Thread(new ThreadTest_BUG2("B"));
        thread2.start();

        Thread thread3 = new Thread(new ThreadTest_BUG2("C"));
        thread3.start();

        Thread thread4 = new Thread(new ThreadTest_BUG2("D"));
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
     *           3、await 用的方式不对，导致死等 BUG2
     * */
}
