package com.cf.demo.service.time;

/**
 * @author liweinan
 * @date 2019-03-30
 */
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InstantExample {

    public static void main(String[] args) {
        //Current timestamp
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = "+timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = "+specificTime);

        //Duration example
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay);


        Date date = new Date(System.currentTimeMillis() + 120000);
        long timeNow = System.currentTimeMillis();

        System.out.println(TimeUnit.MILLISECONDS.toMinutes(date.getTime() - timeNow));

        long maxJoinCount = (long)Integer.MAX_VALUE * 2 + 1L;

        System.out.println(maxJoinCount);


        List<Object> objects = Collections.singletonList(null);
        System.out.println(objects);
    }

}