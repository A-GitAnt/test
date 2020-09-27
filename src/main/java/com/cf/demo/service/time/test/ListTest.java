package com.cf.demo.service.time.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author liweinan
 * @date 2019-09-16
 *
 * https://blog.csdn.net/m0_37451060/article/details/80532275
 */
public class ListTest {
    private static final BigDecimal EXCHANGE_GOLD_GRAM_PER_INTEGRAL = new BigDecimal("0.00001");

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);



        System.out.println(integers.subList(0, 2));
        System.out.println(integers.subList(2, integers.size()));
    }
}
