package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SortArithmetic;

/**
 * 移除元素
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class L27RemoveElement {
    public static void main(String[] args) {
        int[] arrs = new int[]{3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement(arrs,val));
    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int noPeatIndex = 0;
        int noPeatNum = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                noPeatNum = nums[i];
                nums[noPeatIndex] = noPeatNum;
                noPeatIndex++;
            }
        }

        return noPeatIndex;
    }


}
