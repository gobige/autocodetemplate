package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 思路：记录一个位置index和一个位置值value，不断得到下一个index和value，看最终是否得到最后位置index
 */
public class L55JumpingGame {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return false;
        } else if (nums.length == 1) {
            return true;
        }

        int index = 0;
        int value = nums[0];

        while (true) {
            if (value == 0) {
                return false;
            }
            index = index + value;

            if (index == nums.length - 1) {
                return true;
            } else if (index >= nums.length) {
                return false;
            }
            value = nums[index];
        }
    }
}
