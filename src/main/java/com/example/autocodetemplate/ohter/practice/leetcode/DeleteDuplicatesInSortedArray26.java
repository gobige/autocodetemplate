package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SortArithmetic;

/**
 * 删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 思路：先进行排序，然后用两个指针进行遍历，遇到重复的忽略，继续往下找
 * 时间复杂度 +n
 */
public class DeleteDuplicatesInSortedArray26 {
    public static void main(String[] args) {
        int[] arrs = new int[]{1, 1, 2};

        System.out.println(removeDuplicates(arrs));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int noPeatIndex = 0;
        int noPeatNum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (noPeatNum != nums[i]) {
                noPeatNum = nums[i];
                noPeatIndex++;
                nums[noPeatIndex] = noPeatNum;
            }
        }

        return noPeatIndex + 1;
    }
}
