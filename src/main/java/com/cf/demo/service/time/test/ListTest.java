package com.cf.demo.service.time.test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liweinan
 * @date 2019-09-16
 *
 * https://blog.csdn.net/m0_37451060/article/details/80532275
 */
public class ListTest {
    private static final BigDecimal EXCHANGE_GOLD_GRAM_PER_INTEGRAL = new BigDecimal("0.00001");

    public static void main(String[] args) {
        Map<String, Map<String, BigDecimal>> salePriceFactor = new HashMap<>();
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("gb", BigDecimal.valueOf(123));
        salePriceFactor.put("wholee", map);

        System.out.println(JSON.toJSONString(salePriceFactor));


        String s = "{\n" +
                "    \"wholee\":{\n" +
                "        \"gb\":1.1,\n" +
                "        \"us\":1.1,\n" +
                "        \"de\":1.1,\n" +
                "        \"fr\":1.1,\n" +
                "        \"ca\":1.1,\n" +
                "        \"au\":1.1,\n" +
                "        \"it\":1.1,\n" +
                "        \"es\":1.1,\n" +
                "        \"nl\":1.1,\n" +
                "        \"be\":1.1,\n" +
                "        \"at\":1.1\n" +
                "    },\n" +
                "    \"m_wholee\":{\n" +
                "        \"gb\":1.1,\n" +
                "        \"us\":1.1,\n" +
                "        \"de\":1.1,\n" +
                "        \"fr\":1.1,\n" +
                "        \"ca\":1.1,\n" +
                "        \"au\":1.1\n" +
                "    },\n" +
                "    \"shopify_m_wholee\":{\n" +
                "        \"gb\":1.1,\n" +
                "        \"us\":1.1,\n" +
                "        \"de\":1.1\n" +
                "    }\n" +
                "}";
        salePriceFactor = (Map<String, Map<String, BigDecimal>>) JSON.parse(s);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        System.out.println(integers.subList(0, 2));
        System.out.println(integers.subList(2, integers.size()));
    }
}
