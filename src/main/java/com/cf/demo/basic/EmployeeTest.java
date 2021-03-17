package com.cf.demo.basic;

/**
 * @author liweinan
 * @date 2020/11/20
 */
public class EmployeeTest {

    public static void main(String[] args) {
        Boss boss = new Boss("renzhengfei", 78);
        System.out.println(boss.getName());


        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors: " + i);
    }
}
