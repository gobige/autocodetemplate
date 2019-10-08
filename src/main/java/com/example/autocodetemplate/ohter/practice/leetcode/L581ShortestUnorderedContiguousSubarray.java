package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 581最短无序连续子数组
 *
 *给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 输入: [2, 6, 4, 8, 10, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 思路1：这段子数组前面数字永远小于该子数组所有值，后面数字永远大于该子数组所有值
 */
public class L581ShortestUnorderedContiguousSubarray {
    public static void main(String[] args) {

        findUnsortedSubarray(new int[]{1, 2, 3, 4});
    }
    public static int findUnsortedSubarray(int[] nums) {
        Integer left = null;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    left = i;
                    i = nums.length - 1;
                    break;
                }
            }
        }

        Integer right = null;
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    right = i;
                    i = -1;
                    break;
                }
            }
        }

        if (right == null && left == null) {
            return 0;
        }

        return right - left + 1;
    }
}


