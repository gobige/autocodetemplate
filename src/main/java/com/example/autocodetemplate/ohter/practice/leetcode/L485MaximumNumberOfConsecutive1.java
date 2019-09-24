package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * 思路1：遍历 碰到第一个1 做 标记，碰到0取消标记，并记录 连续1 数量，取最大值
 */
public class L485MaximumNumberOfConsecutive1 {

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        boolean flag = false;
        int maxConti = 0;
        int continuous = 0;
        for (int num : nums) {
            // 寻址第一个1
            if (flag == false) {
                // 第一个1出现
                if (num == 1) {
                    continuous++;
                    maxConti = continuous > maxConti ? continuous : maxConti;
                    flag = true;
                }
            } else if (flag == true) {
                // 连续1记录
                if (num == 1) {
                    continuous++;
                    maxConti = continuous > maxConti ? continuous : maxConti;
                } else if (num == 0) {
                    continuous = 0;
                    flag = false;
                }
            }
        }

        return maxConti;
    }
}
