package com.cf.demo.service.S20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异位词 字母出现次数时一样的，但是顺序不一样
 *
 * @author liweinan
 * @date 2020/8/12
 */
public class S49 {
    /* 暴力求解1: sort一遍，看是否相等 */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = Arrays.toString(chars);
            Integer time = map.get(s);
            if (time == null) {
                map.put(s, 1);
            } else {
                map.put(s, time + 1);
            }
        }
        return null;
        // return map.values();
    }

    public static void main(String[] args) {

    }
 }
