package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 496. 下一个更大元素 I
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 思路遍历num1，找出在 nums2中的数字 ，暴力法
 */
public class L496NextBiggerElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {

            boolean flag = false;
            int nextThan = -1;
            for (int j : nums2) {
                if (nums1[i] == j) {
                    flag = true;
                    continue;
                }
                if (flag && j > nums1[i]) {
                    nextThan = j;
                    break;
                }
            }

            result[i] = nextThan;
        }

        return result;
    }
}
