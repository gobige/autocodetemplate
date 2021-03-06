package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 *219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 **/
public class L219DuplicateElement2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                if (Math.abs(map.get(nums[i]) - i) <= k) {
                    return true;
                }
                map.put(nums[i], i);
            }else {
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
