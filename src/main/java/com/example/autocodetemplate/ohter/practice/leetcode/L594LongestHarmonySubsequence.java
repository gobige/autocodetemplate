package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 594. 最长和谐子序列
 *  和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 *
 *  现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 *  思路1 先排序
 */
public class L594LongestHarmonySubsequence {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>(12);

        int size = 0;
        System.out.println(findLHS(new int[]{1, 1, 1, 1}));
        list.trimToSize();
        list.add(new Object());
    }
    public static int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        int preSum = 0;
        int nextSum = 1;
        int max = 0;

        if (nums[0] == nums[nums.length - 1]) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if (Math.abs(nums[i] - nums[i + 1]) == 1) {
                    max = preSum + nextSum > max ? preSum + nextSum : max;
                    preSum = nextSum;
                    nextSum = 1;
                }else {
                    max = preSum + nextSum > max ? preSum + nextSum : max;
                    preSum = 0;
                    nextSum = 1;
                }
            }else {
                nextSum++;
            }
        }

        max = preSum + nextSum > max ? preSum + nextSum : max;

        if (max == 1) {
            return 0;
        }
        return max;
    }
}
