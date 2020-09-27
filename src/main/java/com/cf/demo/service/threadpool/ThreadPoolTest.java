package com.cf.demo.service.threadpool;

import java.util.*;
import java.util.concurrent.*;

/**
 * @param
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Runnable r = () -> {
            System.out.println("start." + Thread.currentThread().getName());
            countDownLatch.countDown();
            int i = 1/0;
            /* 注意上面，一个线程抛异常后，并没有影响后面的线程执行，同时线程池也没有抛异常*/
            System.out.println("over." + Thread.currentThread().getName());
        };


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(r);
        }

        countDownLatch.await();
        System.out.println("all end.");

    }
}
