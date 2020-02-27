package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SearchArithmetic;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 思路：二分查找最终得到，最后得到值的位置和需要插入位置信息
 */
public class L35SearchInsertionLocation {

    public static void main(String[] args) {

    }

    public int searchInsert(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);

        return index;
    }

    public static int binarySearch(int[] soredArrs, int start, int end, int searchNum) {
        if (start <= end) {
            int middleNumIndex = (start + end) / 2;
            if (soredArrs[middleNumIndex] == searchNum) {
                return middleNumIndex;
            } else if (soredArrs[middleNumIndex] > searchNum) {
                return binarySearch(soredArrs, start, middleNumIndex-1, searchNum);
            } else {
                return binarySearch(soredArrs, middleNumIndex+1, end, searchNum);
            }
        } else {
            return start;
        }
    }
}
