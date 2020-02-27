package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SearchArithmetic;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 思路：二分查找目标 如果又找到目标，则对其坐标左右扩展查询，开始位置和结束位置
 */
public class L34FindTheFirstAndLastPositionOfAnElementInASortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(searchRange(nums, 1));
    }


    public static int[] searchRange(int[] nums, int target) {
        int index = SearchArithmetic.binarySearch(nums, 0, nums.length - 1, target);

        int startIndex = -1;
        int endIndex = -1;
        if (index == -1) {
            return new int[]{-1, -1};
        }

        startIndex = index;
        endIndex = index;


        while (startIndex - 1 >= 0) {
            if (nums[startIndex - 1] == target) {
                startIndex--;
            }else {
                break;
            }
        }

        while (endIndex + 1 < nums.length) {
            if (nums[endIndex + 1] == target) {
                endIndex++;
            }else {
                break;
            }
        }


        int[] retNums = new int[2];
        retNums[0] = startIndex;
        retNums[1] = endIndex;

        return retNums;
    }

}
