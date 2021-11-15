package com.cf.demo.service.concurrent.S2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 ReentrantLock 中 Condition的 await/signal 机制
 */

public class EvenThreadV2 {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static int count = 0;

    static class Worker implements Runnable {
        private String workerType;

        public Worker(String type) {
            workerType = type;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (((count & 0x01) == 1 && workerType.equals("even"))
                            || ((count & 0x01) == 0 && workerType.equals("odd"))) {
                        condition.await();
                    }

                    if (count > 100) {
                        return;
                    }

                    System.out.println(Thread.currentThread() + String.valueOf(count));
                    count += 1;
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }

        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread odd = new Thread(new Worker("odd"));
        odd.start();

        Thread even = new Thread(new Worker("even"));
        even.start();

        System.out.println("total Cost: " + (System.currentTimeMillis() - start));
    }

}
