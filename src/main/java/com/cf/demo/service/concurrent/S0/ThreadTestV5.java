package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 利用wait/notify 机制  (ReentrantLock)

 */
public class ThreadTestV5 {

    private static final AtomicInteger count = new AtomicInteger();

    private static final ReentrantLock reentrantLock = new ReentrantLock();

    private static final Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        final Object lock = new Object();

        class Worker implements Runnable {
            private AtomicInteger ai;

            public Worker(AtomicInteger i) {
                this.ai = i;
            }

            @Override
            public void run() {
                System.out.println("invoke RPC:" + Thread.currentThread());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                ai.incrementAndGet();
                if (ai.get() == 10) {

                    try {
                        reentrantLock.lock();
                        condition.signalAll();
                    } finally {
                        reentrantLock.unlock();
                    }


                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(count));
            thread.start();
        }

        try {
            reentrantLock.lock();
            condition.await();
        } finally {
            reentrantLock.unlock();
        }

        System.out.println("all thread end,continue...");
   }
}
