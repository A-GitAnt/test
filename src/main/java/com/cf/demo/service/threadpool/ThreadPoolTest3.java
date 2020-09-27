package com.cf.demo.service.threadpool;

import com.cf.demo.service.threadpool.util.CommonThreadFactory;
import com.cf.demo.service.threadpool.util.CommonThreadPoolExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param
 */
public class ThreadPoolTest3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        AtomicInteger incr = new AtomicInteger();

        Callable<Integer> r = () -> {
            System.out.println("start." + Thread.currentThread().getName());

            for (int i = 0; i < 10; i++) {
                incr.incrementAndGet();
            }
            countDownLatch.countDown();
            System.out.println("end." + Thread.currentThread().getName() + " "+ incr.get());
            return incr.get();
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


        Integer total = 0;
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = dealChangeExecutor.submit(r);
            try {
                Integer result = future.get();
                if (result != null) {
                    total += result;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        countDownLatch.await();
        System.out.println("all end. final result: " + total + "incr: " + incr.get());

    }
}
