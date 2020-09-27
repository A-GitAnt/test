package com.cf.demo.service.threadpool;

import com.cf.demo.service.threadpool.util.CommonThreadFactory;
import com.cf.demo.service.threadpool.util.CommonThreadPoolExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Callable<String> r = () -> {
            System.out.println("start." + Thread.currentThread().getName());
            countDownLatch.countDown();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + "got result!";
            // throw new RuntimeException("Thread.currentThread().getName() exception");
        };

        CommonThreadPoolExecutor dealChangeExecutor = new CommonThreadPoolExecutor(
                "dealMemberRightChangeExecutor",
                Runtime.getRuntime().availableProcessors() * 2,
                10,
                30,
                new ArrayBlockingQueue<>(20),
                new CommonThreadFactory("dealMemberChangeExecutor"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 10; i++) {
            Future<String> future = dealChangeExecutor.submit(r);
            try {
                String result = future.get();
                System.out.println("result: " + result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        countDownLatch.await();
        int exceptionCount = dealChangeExecutor.getExceptionCount();
        long avgExecuteTime = dealChangeExecutor.getAvgExecuteTime();

        System.out.println("all end. exceptionCount:" + exceptionCount + "\n avgExecuteTime:" + avgExecuteTime);

    }
}
