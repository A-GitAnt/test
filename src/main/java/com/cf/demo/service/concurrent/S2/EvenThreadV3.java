package com.cf.demo.service.concurrent.S2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 volatile 变量控制顺序
 */

public class EvenThreadV3 {

    /* count 没有必要用 volatile修饰
     *  具体原因见 OneNote -> 技术提升 -> 并发 */
    private static Integer count = 0;

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
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = false;
                } else if (!flag && workerType.equals("even")) {
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = true;
                }

                if (count > 100) {
                    return;
                }
            }
        }
    }


    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println(StrToDate("2020-04-11 23:59:59"));

        new Thread(new Worker("even")).start();
        new Thread(new Worker("odd")).start();
        System.out.println("total Cost: " + (System.currentTimeMillis() - start));
    }

}
