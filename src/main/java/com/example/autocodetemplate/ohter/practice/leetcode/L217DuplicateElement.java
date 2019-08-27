package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 217. 存在重复元素
 *
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 */
public class L217DuplicateElement {
    public boolean containsDuplicate(int[] nums) {

        Set map = new HashSet<>(nums.length);

        for (int num : nums) {
            if (map.contains(num)) {
                return true;
            }else {
                map.add(num);
            }
        }

        return false;
    }
}
