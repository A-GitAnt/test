package com.cf.demo.service.time.test;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@Data
public class IntegralInfoVO implements Serializable {
    private Long uid;

    private String integralType;

    private BigDecimal goldInGram;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<Integer> integerClass = Integer.class;
        Field digits = integerClass.getField("MIN_VALUE");
        String name = digits.getName();
        Class<?> type = digits.getType();


        IntegralInfoVO integralInfoVO = new IntegralInfoVO();
        integralInfoVO.setUid(1234L);

        Class<IntegralInfoVO> integralInfoVOClass = IntegralInfoVO.class;

        Field uidFiled = integralInfoVOClass.getDeclaredField("uid");

        Long userid = (Long)uidFiled.get(integralInfoVO);

        // Constructor<?>[] constructors = integerClass.getConstructors();
        // for (Constructor<?> constructor : constructors) {
        //     constructor.newInstance();
        // }

        uidFiled.setAccessible(true);
        uidFiled.set(integralInfoVO, 413L);


        userid = (Long)uidFiled.get(integralInfoVO);

        Field declaredField = integerClass.getDeclaredField("MIN_VALUE");


        Method method = integralInfoVOClass.getMethod("setUid", Long.class);

        method.invoke(integralInfoVO, 666L);

        userid = (Long)uidFiled.get(integralInfoVO);


        digits.equals("qa");

        String s = tableSuffix(450010873L);
        System.out.println(s);


        System.out.println(conventTime(System.currentTimeMillis()));
    }

    private static String tableSuffix(Long userId) {
        String hex = Integer.toHexString(userId.intValue() & 255);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex.toLowerCase();
    }





    private static Date conventTime(long time) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        return new Date(time);
    }
}
