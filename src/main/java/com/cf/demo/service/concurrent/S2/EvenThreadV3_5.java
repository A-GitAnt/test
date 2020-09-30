package com.cf.demo.service.concurrent.S2;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 volatile 变量控制顺序
 */

public class EvenThreadV3_5 {

    /* count 没有必要用 volatile修饰
     *  具体原因见 OneNote -> 技术提升 -> 并发 */
    private static  Integer count = 0;

    private static volatile boolean flag = true;

    static class Worker implements Runnable {

        private String workerType;

        public Worker(String type) {
            workerType = type;
        }

        @Override
        public void run() {

            while (true) {

                if (flag && workerType.equals("odd")) {
                    if (count > 100) {
                        return;
                    }
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = false;
                }

                if (!flag && workerType.equals("even")) {
                    if (count > 100) {
                        return;
                    }
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = true;
                }

            }
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        new Thread(new Worker("even")).start();
        new Thread(new Worker("odd")).start();

        System.out.println("total Cost: " + (System.currentTimeMillis() - start));
    }

}
