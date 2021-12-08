package com.cf.demo.service.concurrent.S2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 交替打印奇偶数（0~100），两个线程
 *
 * 使用 volatile 变量控制顺序
 *
 * https://zhuanlan.zhihu.com/p/47948392
 */

public class EvenThreadV4 {
    /* count 没有必要用 volatile修饰
    *  具体原因见 OneNote -> 技术提升 -> 并发 */
    private static int count = 0;

    /* 不用flag可以吗？

           不可以，去掉每个线程虽然打的数据对，但是没有顺序性了
     * */
    private volatile static boolean flag = true;


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            while (count < 100) {
                if (flag) {
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = false;
                }
            }
        });


        executorService.execute(() -> {
            while (count < 100) {
                if (!flag) {
                    System.out.println(Thread.currentThread() + String.valueOf(count++));
                    flag = true;
                }
            }
        });
    }
}
