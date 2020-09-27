package com.cf.demo.service.threadpool;

import com.cf.demo.service.threadpool.util.CommonThreadFactory;
import com.cf.demo.service.threadpool.util.CommonThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * @param
 */
public class ThreadPoolTest1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Runnable r = () -> {
            System.out.println("start." + Thread.currentThread().getName());
            countDownLatch.countDown();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Thread.currentThread().getName() exception");
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


            dealChangeExecutor.execute(r);
            /*
            * 为什么 execute 会直接在
            *
            * java.util.concurrent.ThreadPoolExecutor.runWorker 中捕获到异常
            *
            * 而  submit 会在相同的位置，无法捕获异常呢？
            *
            * 答：因为在 submit时，newTaskFor中把线程 包装成 FutureTask 对象，其run 方法中把异常 catch住了，所以外面执行时捕获不到
            *
            *
                  public <T> Future<T> submit(Callable<T> task) {
                    if (task == null) throw new NullPointerException();
                    RunnableFuture<T> ftask = newTaskFor(task);
                    execute(ftask);
                    return ftask;
                  }

            *
            * */

        }

        countDownLatch.await();
        int exceptionCount = dealChangeExecutor.getExceptionCount();
        long avgExecuteTime = dealChangeExecutor.getAvgExecuteTime();

        System.out.println("all end. exceptionCount:" + exceptionCount + "\n avgExecuteTime:" + avgExecuteTime);

    }
}
