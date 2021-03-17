package com.cf.demo.service.concurrent.S2;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 volatile 变量控制顺序
 */

public class TT {

    static ReentrantLock reentrantLock = new ReentrantLock();

    static Condition condition = reentrantLock.newCondition();

    static AtomicInteger i = new AtomicInteger(0);

    static class Worker implements Runnable {

        private String type;

        public Worker(String type) {
            this.type = type;
        }

        @Override
        public void run() {

            while (true) {
                reentrantLock.lock();
                try {
                    while ( ((i.get() & 0x01) == 0 && "even".equals(type)) ||
                            ((i.get() & 0x01) == 1 && "odd".equals(type))) {
                        condition.await();
                    }

                    i.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    if (i.get() >= 100) {
                        return;
                    }
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Worker("odd")).start();
        new Thread(new Worker("even")).start();

    }

}
