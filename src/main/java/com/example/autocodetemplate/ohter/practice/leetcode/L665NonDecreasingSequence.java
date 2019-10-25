package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.mail.Flags;

/**
 * 665. 非递减数列
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 *
 * 思路1：判断 有且只有一个数比他后面的数大 则 true
 */
public class L665NonDecreasingSequence {
    public boolean checkPossibility(int[] nums) {
        boolean flag = true;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (flag) {
                    flag = false;
                }else {
                    return false;
                }
            }
        }

        return true;
    }
}
