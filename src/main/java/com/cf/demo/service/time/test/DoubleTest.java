package com.cf.demo.service.time.test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author liweinan
 * @date 2019-09-16
 */
public class DoubleTest {
    private static final BigDecimal EXCHANGE_GOLD_GRAM_PER_INTEGRAL = new BigDecimal("0.00001");

    public static void main(String[] args) {

        IntegralInfoVO integralInfoVO = new IntegralInfoVO();
        Double aDouble = Double.valueOf("1.43");
        BigDecimal bg = new BigDecimal(aDouble);
        BigDecimal bigDecimal = bg.setScale(2, BigDecimal.ROUND_HALF_DOWN);

        System.out.println(bigDecimal.doubleValue());

        // integralInfoVO.setGoldInGram(bg);
        //
        // System.out.println(bigDecimal.toPlainString());
        // System.out.println(integralInfoVO);
        //
        // System.out.println(EXCHANGE_GOLD_GRAM_PER_INTEGRAL);
        //
        // // System.out.println(EXCHANGE_GOLD_GRAM_PER_INTEGRAL.toPlainString().split("E-")[1].length());
        //
        // List<Object> objects = Collections.emptyList();
        // System.out.println(objects.get(0));
    }
}
