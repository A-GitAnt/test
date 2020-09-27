package com.cf.demo.service.time.test;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liweinan
 * @date 2019-09-16
 *
 * https://blog.csdn.net/m0_37451060/article/details/80532275
 */
public class IntegerTest {
    private static final BigDecimal EXCHANGE_GOLD_GRAM_PER_INTEGRAL = new BigDecimal("0.00001");

    public static void main(String[] args) {
       

        Integer a = 1000;
        Integer b = 1000;
        Integer c = 100;
        Integer d = 100;
        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println(a == 1000);



        Integer a1 = new Integer(1000);
        Integer c1 = new Integer(10);
        Integer d1 = new Integer(10);
        int b1 = 1000;

        System.out.println(a1 == b1);
        System.out.println(c1 == d1);
    }
}
