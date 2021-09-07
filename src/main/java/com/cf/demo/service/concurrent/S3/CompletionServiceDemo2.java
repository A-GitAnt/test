package com.cf.demo.service.concurrent.S3;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 Synchronized 锁的 wait/notify 机制
 *
 */

public class CompletionServiceDemo2 {


    /**
     * 模拟请求某猪网站 爬取机票信息
     *
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouZhuFlightPrice() throws InterruptedException {
        // 模拟请求某猪网站 爬取机票信息
        Thread.sleep(10000);
        return "获取到某猪网站的机票信息了";
    }

    /**
     * 模拟请求某携网站 爬取机票信息
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouXieFlightPrice() throws InterruptedException {
        // 模拟请求某携网站 爬取机票信息
        Thread.sleep(5000);
        return "获取到某携网站的机票信息了";
    }


    /**
     * 模拟请求团网站 爬取机票信息
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouTuanFlightPrice() throws InterruptedException {
        // 模拟请求某团网站 爬取机票信息
        Thread.sleep(3000);
        return "获取到某团网站的机票信息了";
    }

    final static ExecutorService executor = new ThreadPoolExecutor(6, 6,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletionService completionService = new ExecutorCompletionService(executor);
        completionService.submit(() -> getMouZhuFlightPrice());
        completionService.submit(() -> getMouXieFlightPrice());
        completionService.submit(() -> getMouTuanFlightPrice());

        for (int i = 0; i < 3; i++) {
            String result = (String)completionService.take().get();
            System.out.println(result);
        }
    }



}
