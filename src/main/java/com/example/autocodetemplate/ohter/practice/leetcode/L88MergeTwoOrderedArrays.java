package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 思路：使用两个指针遍历两个数组
 */
public class L88MergeTwoOrderedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < m && index2 < n) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                // 互换num1和num2数值
                nums1[index1] = nums1[index1] + nums2[index2];
                nums2[index2] = nums1[index1] - nums2[index2];
                nums1[index1] = nums1[index1] - nums2[index2];

                index1++;

                while (nums2[index2] > nums2[index2 + 1] && index2 < n) {
                    nums2[index2] = nums2[index2] + nums2[index2+1];
                    nums2[index2 + 1] = nums2[index2] - nums2[index2 + 1];
                    nums2[index2] = nums2[index2] - nums2[index2+1];
                    index2++;
                }
            }
        }

        if (index1 == m) {
            while (index2 < n) {
                nums1[index1] = nums2[index2];
                index2++;
                index1++;
            }
        }
        if (index2 == n) {
            while (index1 < m) {
                nums1[index1] = nums2[index2];
                index2++;
                index1++;

                //TODO
            }
        }
    }
}
