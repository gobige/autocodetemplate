package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class L303RegionAndSearchArrayIsImmutable {
    Map<String, Integer> map = new HashMap();
    int[] nums;
    public L303RegionAndSearchArrayIsImmutable(int[] nums) {
       this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if (map.containsKey(i + "" + j)) {
            return map.get(i + "" + j);
        }

        int total = 0;
        while (i <= j) {
            total += nums[i];
        }
        map.put(i + "" + j, total);

        return total;
    }
}
