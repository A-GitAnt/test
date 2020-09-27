package com.cf.demo.service.time.test;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liweinan
 * @date 2019-09-16
 */
public class AvatorTest {
    private static final BigDecimal EXCHANGE_GOLD_GRAM_PER_INTEGRAL = new BigDecimal("0.00001");

    public static void main(String[] args) {
        String s = "('http://f.cfcdn.club/cf-app-rn/imgs/home_friends_deal/avatar%s.png'),";

        for (int i = 1; i < 217; i++) {
            System.out.println(String.format(s, i));
        }

        Map<String, String> map = new HashMap<>();
        map.put("7019513", "143");
        map.put("7019517", "71.5");
        map.put("7019411", "14.3");
        map.put("7019413", "7.15");
        map.put("5350050", "1.43");
        map.put("5350054", "0.715");
        map.put("5350061", "0.143");

        System.out.println(map);

        Gson gson = new Gson();

        Map<String, String> map1 = gson.fromJson(map.toString(), Map.class);
        System.out.println(map1);


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
        System.out.println(a1 == b);
        System.out.println(c1 == d1);
    }
}
