package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 利用wait/notify 机制  (ReentrantLock)

 */
public class ThreadTestVV {

    static final ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    static AtomicInteger atomicInteger = new AtomicInteger();



    static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("invoke:" + Thread.currentThread().getId());
            atomicInteger.getAndIncrement();
            if (atomicInteger.get() == 10) {

                lock.lock();

                try {

                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread()).start();
        }

        lock.lock();
        try {
            condition.await();
            System.out.println("all done.");
        } finally {
            lock.unlock();
        }
    }


}
