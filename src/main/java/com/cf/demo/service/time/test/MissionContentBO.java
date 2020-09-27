package com.cf.demo.service.time.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

@Data
public class MissionContentBO  {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 任务有效期
     * */
    private Long expireSeconds;


    /* ----group-user类型有以下值-------- */

    /**
     * 需要参团的人数
     * */
    private Integer groupUser;

    /**
     * 团里需要几个新人*/
    private Integer groupNewUser;

    /* ----end-------- */

    /* ----group-grand类型有以下值-------- */

    /*
    * 小团的过期时间
    * */
    private Integer expireSecondsPerGroup;

    /*
     * 小团数量
     * */
    private Integer groupsRequired;

    /*
     * 小团要求新用户数
     * */
    private Integer newUserNumPerGroup;

    /*
     * 小团要求总用户数
     * */
    private Integer totalUserNumPerGroup;

    /* ----end-------- */

    public static void main(String[] args) {
        String mb = "{\"expireSecondsPerGroup\":172800.0,\"groupsRequired\":2000,\"newUserNumPerGroup\":2,\"totalUserNumPerGroup\":6}";
        MissionContentBO missionContentBO = toObject(mb, MissionContentBO.class);
        System.out.println(missionContentBO);

        test(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String json, Class<T> type) {
        long start  = System.currentTimeMillis();
        T t = null;
        if (json == null) {
            return null;
        }
        try {
            t = (T) gson.fromJson(json, type);
        } catch (Exception e) {
        }
        return t;
    }

    static void test(Boolean b) {
        if (b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
