package com.cf.demo.service.concurrent.S2;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 Synchronized 锁的 wait/notify 机制
 *
 */

public class EvenThread {

    final static Object lock = new Object();

    private static int count = 0;

    static class Worker implements Runnable {

        private String workerType;

        public Worker(String type) {
            workerType = type;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    int i = 0;
                    while (((i = count & 0x01) == 0 && workerType.equals("even"))
                            || (i= count & 0x01) == 1 && workerType.equals("odd")) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (count > 100) {
                        return;
                    }

                    System.out.println(Thread.currentThread()  + String.valueOf(count));
                    count += 1;
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread odd = new Thread(new Worker("odd"));
        odd.start();

        Thread even = new Thread(new Worker("even"));
        even.start();

        odd.join();
        even.join();
        System.out.println("total Cost: " + (System.currentTimeMillis() - start));
    }

}
