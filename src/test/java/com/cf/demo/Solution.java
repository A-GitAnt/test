package com.cf.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liweinan
 * @date 2020/7/20
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(list);
        List<List<Integer>> result = new ArrayList();
        for (int i = 0; i < list.size() - 2; i++) {
            if (i == 0 || (i > 0 && list.get(i) != list.get(i - 1))) {
                int target = -list.get(i);
                if (target < 0) {
                    break;
                }
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = list.get(l) + list.get(r);
                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        result.add(Arrays.asList(list.get(i), list.get(l), list.get(r)));
                        while (l < r && list.get(l) == list.get(l + 1)) {
                            l++;
                        }
                        while (l < r && list.get(r) == list.get(r - 1)) {
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

