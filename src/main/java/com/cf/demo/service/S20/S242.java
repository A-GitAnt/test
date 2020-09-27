package com.cf.demo.service.S20;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 异位词 字母出现次数时一样的，但是顺序不一样
 *
 * @author liweinan
 * @date 2020/8/12
 */
public class S242 {
    /* 暴力求解1: sort一遍，看是否相等 */
    /* 时间复杂度：O(n log(n))，双轴快排 */
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t1);
        return Arrays.equals(s1, t1);
    }


    /* 暴力求解2: hashMap 统计字符出现的频次 是否相同 */
    public boolean isAnagram2(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (char s1 : sArray) {
            map1.merge(s1, 1, Integer::sum);
        }

        for (char t1 : tArray) {
            map2.merge(t1, 1, Integer::sum);
        }

        return map1.equals(map2);
    }

    public static void main(String[] args) {

    }
 }
