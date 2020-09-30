package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 利用wait/notify 机制  (对象级别加锁)

 */
public class ThreadTestV4 {

    private static final AtomicInteger count = new AtomicInteger();

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
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(count));
            thread.start();
        }

        synchronized (lock) {
            lock.wait();
        }

        System.out.println("all thread end,continue...");
   }
}
