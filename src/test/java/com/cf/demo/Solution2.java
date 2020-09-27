package com.cf.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leet-code No.15
 * @author liweinan
 * @date 2020/7/20
 */
class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = -nums[i];
                if (target < 0) {
                    break;
                }
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[l] + nums[r];
                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return result;
    }
}

