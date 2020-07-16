package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 */
public class L78SonColle {
    /**
     * 双指针
     * 时间复杂度 N的阶乘
     * 空间复杂度 最大3N 最小N
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List resultList = new ArrayList<List<Integer>>();

        int length = nums.length;
        if (length < 1) {
            resultList.add(new ArrayList<>());
            return resultList;
        }

        for (int i = 1; i <= length; i++) {

        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{}));
    }
}
