package com.cf.demo.service.time.test;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@Data
public class SQL implements Serializable {
    private Long uid;

    private String integralType;

    private BigDecimal goldInGram;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        // for (int i = 0; i <= 255; i++) {
        //     StringBuilder builder = new StringBuilder();
        //     builder.append("ALTER TABLE coupon_");
        //     builder.append(tableSuffix(i));
        //     builder.append(" ADD COLUMN coupon_code varchar(64) binary DEFAULT NULL COMMENT '优惠券的券码';");
        //
        //     System.out.println(builder.toString());
        // }

        System.out.printf(tableSuffix(474012106));

    }

    private static String tableSuffix(int userId) {
        String hex = Integer.toHexString(userId & 255);
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
