package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 思路1 使用双指针，指针a找0，指针b找0后面最近的非0值,然后交换，知道
 */
public class L283MovingZero {
    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});

    }

    public static void moveZeroes(int[] nums) {
        int findZeroIndex = 0;
        int findNonZeroIndex = 0;

        while (findZeroIndex < nums.length) {
            if (nums[findZeroIndex] == 0) {
                findNonZeroIndex = findZeroIndex;

                while (findNonZeroIndex < nums.length) {
                    if (nums[findNonZeroIndex] != 0) {
                        nums[findZeroIndex] = nums[findZeroIndex] + nums[findNonZeroIndex];
                        nums[findNonZeroIndex] = nums[findZeroIndex] - nums[findNonZeroIndex];
                        nums[findZeroIndex] = nums[findZeroIndex] - nums[findNonZeroIndex];
                        break;
                    }
                    findNonZeroIndex++;
                }
            }
            findZeroIndex++;
        }
    }

}