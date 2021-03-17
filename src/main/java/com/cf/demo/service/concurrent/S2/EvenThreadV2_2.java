package com.cf.demo.service.concurrent.S2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liweinan
 * @date 2021/3/17
 */
public class EvenThreadV2_2 {
    private static final ReentrantLock lock = new  ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static  int i = 0;

    static class MyThread implements Runnable {

        private boolean handleOdd;

        public MyThread(boolean handleOdd) {
            this.handleOdd = handleOdd;
        }

        @Override
        public void run() {
            while (i < 100) {
                lock.lock();

                try {
                    if (handleOdd) {
                        while (i < 100 && i % 2 == 0) {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        while (i < 100 && i % 2 == 1) {
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    System.out.println(i);
                    i++;
                    condition.signal();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread(true));
        thread.start();

        Thread thread2 = new Thread(new MyThread(false));
        thread2.start();
    }



}
