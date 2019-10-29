package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.mail.Flags;

/**
 * 665. 非递减数列
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 *
 * 思路1：判断 有且只有一个数比他后面的数大 则 true,因为存在 例如 4,2,3这种情况，所以在遍历过程中要将做改变元素的动作
 */

public class L665NonDecreasingSequence {
    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{  3, 4, 2, 3}));
    }
    public static boolean checkPossibility(int[] nums) {
        if (nums.length <3) {
            return true;
        }


        boolean flag = true;
        if (nums[0] > nums[1]) {
            nums[0] = nums[1];
            flag = false;
        }

        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (flag) {
                    if (nums[i - 1] > nums[i + 1]) {
                        nums[i] = nums[i - 1];
                    }else {
                        nums[i] = nums[i + 1];
                    }
                    i--;
                    flag = false;
                }else {
                    return false;
                }
            }
        }

        return true;
    }
}
