package com.cf.demo.service.concurrent.S2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 volatile 变量控制顺序
 */

public class EvenThreadV3 {

    /* 不用flag可以吗？

       不可以，去掉每个线程虽然打的数据对，但是没有顺序性了
    * */
    private static volatile int flag = 0;


    private static AtomicInteger ai = new AtomicInteger();

    static class Worker implements Runnable {

        private String workerType;

        public Worker(String type) {
            workerType = type;
        }

        @Override
        public void run() {
            while (true) {
                int count = ai.get();
                if (count > 100) {
                    return;
                }
                int i = count & 0x01;

                if (i == 0 && workerType.equals("odd") && flag == 0) {
                    System.out.println(Thread.currentThread() + String.valueOf(ai.getAndIncrement()));
                    flag = 1;
                } else if (i == 1 && workerType.equals("even") && flag == 1) {
                    System.out.println(Thread.currentThread() + String.valueOf(ai.getAndIncrement()));
                    flag = 0;
                }

            }
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        new Thread(new Worker("odd")).start();
        new Thread(new Worker("even")).start();

        System.out.println("total Cost: " + (System.currentTimeMillis() - start));
    }

}
