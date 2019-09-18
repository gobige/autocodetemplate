package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 453. 最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *  思路1 运用相对论 ，每次n-1个元素增加1，相当于 每次 一个元素减1
 *
 */
public class L453TheMinimumNumberOfMovesMakesTheArrayElementsEqual {

    public int minMoves(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        int times = 0;
        for (int num : nums) {
            times += num - min;
        }

        return times;
    }

}

