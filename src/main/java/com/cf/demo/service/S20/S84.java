package com.cf.demo.service.S20;

import java.time.ZoneId;
import java.time.zone.ZoneRules;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 *
 * @author liweinan
 * @date 2020/8/12
 */
public class S84 {

    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int scale = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]){
                left--;
            }

            int right = i;
            while (right < heights.length - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }
            int width = right - left + 1;
            scale = Math.max(width * heights[i], scale);
        }
        return scale;
    }

    public static void main(String[] args) {
    }
}
