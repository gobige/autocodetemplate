package com.example.autocodetemplate.ohter.practice.leetcode;

import java.math.BigDecimal;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: 寻找两个有序数组的中位数</p>
 * <p>JDK version used JDK1.8</p>
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *
 * 解题思路：先合并有序数组，再获取中位数
 * @version 1.0
 */
public class MedianSortedArraysFind4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 5};
        int[] nums2 = {2, 4, 9, 10};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 获取中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] combinSortArr = combineArrays(nums1, nums2);

        if (combinSortArr != null && combinSortArr.length > 0) {
            // 奇数
            boolean oddNum = combinSortArr.length % 2 == 0 ? false : true;

            int mediaIndex = combinSortArr.length / 2;
            if (oddNum) {
                return new Double(combinSortArr[mediaIndex]);
            }else {
                Double divisor =  new Double((combinSortArr[mediaIndex] + combinSortArr[mediaIndex - 1]));
                Double divisor2 = new Double(2);
                return divisor / divisor2;
            }
        }

        return 0;
    }

    /**
     * 归并排序
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] combineArrays(int[] nums1, int[] nums2) {
        int[] combinSortArr = new int[nums1.length + nums2.length];

        int nums1i = 0;
        int nums2i = 0;
        int combinSortArri = 0;
        while (!(nums1i == nums1.length && nums2i == nums2.length)) {
            if (nums1i == nums1.length && nums2i < nums2.length) {
                for (; nums2i < nums2.length; ) {
                    combinSortArr[combinSortArri] = nums2[nums2i];
                    combinSortArri++;
                    nums2i++;
                }
                break;
            }
            if (nums1i < nums1.length && nums2i == nums2.length) {
                for (; nums1i < nums1.length; ) {
                    combinSortArr[combinSortArri] = nums1[nums1i];
                    combinSortArri++;
                    nums1i++;
                }
                break;
            }
            if (nums1[nums1i] < nums2[nums2i]) {
                combinSortArr[combinSortArri] = nums1[nums1i];
                nums1i++;
                combinSortArri++;
            } else {
                combinSortArr[combinSortArri] = nums2[nums2i];
                nums2i++;
                combinSortArri++;
            }
        }

        return combinSortArr;
    }
}
