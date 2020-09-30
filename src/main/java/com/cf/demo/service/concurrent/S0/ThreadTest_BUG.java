package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.CountDownLatch;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 1、利用CDL
 */
public class ThreadTest_BUG {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        class Worker implements Runnable {

            @Override
            public void run() {
                System.out.println("invoke RPC:" + Thread.currentThread());
                // try {
                //     Thread.sleep(1000);
                // } catch (InterruptedException e) {
                // }
                countDownLatch.countDown();
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker());
            thread.start();
        }

        /*
        * 总结这里为什么会抛异常？？？
        *
        * await !!! 不是wait
        * */
        countDownLatch.wait();

        System.out.println("all thread end,continue...");
   }
}
