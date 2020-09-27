package com.cf.demo.service.S20;

/**
 * 滑动窗口最大值
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 *
 * @author liweinan
 * @date 2020/8/12
 */
public class S239 {
    /* 暴力求解 */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int m = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[m++] = max;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};

        int[] max = maxSlidingWindow(nums, 3);
    }
 }
