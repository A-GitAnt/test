package com.cf.demo.service.time.test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author liweinan
 * @date 2021/9/29
 */
public class TimeZoneTest {

    private static Date conventTime(long time, String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        return new Date(time);
    }

    private static Date conventTime2(long targetTime, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(targetTime));
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar.getTime();
    }

    private static Date conventTime3(long targetTime, String timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar.getTime();
    }


    public static void main(String[] args) {
        System.out.println(conventTime(System.currentTimeMillis(), "Europe/London"));
        System.out.println(conventTime2(System.currentTimeMillis(), "Europe/London"));
        // System.out.println(conventTime(System.currentTimeMillis(), "America/New_York"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Berlin"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Paris"));
        // System.out.println(conventTime(System.currentTimeMillis(), "America/Toronto"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Australia/Sydney"));
        //
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Rome"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Madrid"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Amsterdam"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Brussels"));
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/Vienna"));
        // test3();
        //
        // System.out.println(conventTime(System.currentTimeMillis(), "Europe/London").getTime());
        // System.out.println(new Date(conventTime(System.currentTimeMillis(), "Europe/London").getTime()).toString());
        //
        // getCountryEnum 获取时区！
        // System.out.println(TimeZoneUtil.transformTimeZone2(new Date(), TimeZoneUtil.getUTC(), TimeZone.getTimeZone("Europe/London")));

    }

    public static void test3() {
        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println("可用zoneId总数：" + availableIDs.length);
        for (String zoneId : availableIDs) {
            System.out.println(zoneId);
        }
    }

}
