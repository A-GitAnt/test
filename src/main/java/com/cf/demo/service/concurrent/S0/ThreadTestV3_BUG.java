package com.cf.demo.service.concurrent.S0;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10个线程，分别调用10个接口，主线程等待10个线程都完成后继续
 *
 * 利用synchronized 的 wait/notify 机制 (类级别加锁)
 *
 * Q: 弄清楚为什么抛 IllegalMonitorStateException ？
 * A: 因为 synchronized() 入参指定的锁是 ThreadTestV3_BUG.class （类级别）
 *    而却在 ThreadTestV3_BUG 的实例对象上执行的 wait/notify
 *
 * https://developer.aliyun.com/article/635887
 */
public class ThreadTestV3_BUG {

    private static final AtomicInteger count = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {

        ThreadTestV3_BUG threadTestV3 = new ThreadTestV3_BUG();


        class Worker implements Runnable {
            private AtomicInteger ai;

            public Worker(AtomicInteger i) {
                this.ai = i;
            }

            @Override
            public void run() {
                System.out.println("invoke RPC:" + Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                ai.incrementAndGet();
                if (ai.get() == 10) {
                    synchronized (ThreadTestV3_BUG.class) {
                        threadTestV3.notifyAll();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(count));
            thread.start();
        }


        synchronized (ThreadTestV3_BUG.class) {
            threadTestV3.wait();
        }


        System.out.println("all thread end,continue...");
   }
}
