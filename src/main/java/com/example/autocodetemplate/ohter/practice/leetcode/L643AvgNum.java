package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 643. 子数组最大平均数 I
 *
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 */
public class L643AvgNum {

    public double findMaxAverage(int[] nums, int k) {
        int length = nums.length;
        if (length < 1) {
            return 0;
        }

        double sum = 0;
        if (length < k + 1) {
            for (int num : nums) {
                sum = num + sum;
            }

            return sum / length;
        }

        for (int i = 0; i < k; i++) {
            sum += nums[i] ;
        }

        double maxAvg = sum / k;
        for (int i = k; i < length; i++) {
            sum = sum - nums[i - k] + nums[i];
            if (sum / k > maxAvg) {
                maxAvg = sum / k;
            }
        }

        return maxAvg;
    }
}
