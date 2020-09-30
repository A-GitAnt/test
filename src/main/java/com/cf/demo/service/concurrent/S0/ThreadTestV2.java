package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.CountDownLatch;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 1、利用 CountDownLatch
 *
 * 2、把 countDownLatch 通过构造器传入 Worker线程
 */
public class ThreadTestV2 {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);


    public static void main(String[] args) throws InterruptedException {

        class Worker implements Runnable {

            CountDownLatch countDownLatch;
            public Worker(CountDownLatch cdl) {
                this.countDownLatch = cdl;
            }

            @Override
            public void run() {
                System.out.println("invoke RPC:" + Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(countDownLatch));
            thread.start();
        }

        countDownLatch.await();

        System.out.println("all thread end,continue...");
   }
}
