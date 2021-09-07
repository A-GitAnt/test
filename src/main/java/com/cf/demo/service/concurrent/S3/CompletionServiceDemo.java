package com.cf.demo.service.concurrent.S3;

/**
 * @author liweinan
 * @date 2021/4/30
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author denny.zhang
 * @ClassName: CompletionServiceDemo
 * @Description: CompletionService多线程并发任务结果归集
 * @date 2016年11月4日 下午1:50:32
 */
public class CompletionServiceDemo {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启3个线程
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            int taskCount = 10;
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            //1.定义CompletionService
            CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exs);
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
            //2.添加任务
            for (int i = 0; i < taskCount; i++) {
                futureList.add(completionService.submit(new Task(i + 1)));
            }

            //使用内部阻塞队列的take()
            for (int i = 0; i < taskCount; i++) {
                Integer result = completionService.take().get();//采用completionService.take()，内部维护阻塞队列，任务先完成的先获取到
                System.out.println("任务i==" + result + "完成!" + new Date());
                list.add(result);
            }
            System.out.println("list=" + list);
            System.out.println("总耗时=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();//关闭线程池
        }

    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            if (i == 5) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行完成！");
            return i;
        }

    }
}
