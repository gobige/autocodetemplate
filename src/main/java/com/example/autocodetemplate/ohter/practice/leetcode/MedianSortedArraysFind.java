package com.example.autocodetemplate.ohter.practice.leetcode;

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
 * @version 1.0
 */
public class MedianSortedArraysFind {
    public static void main(String[] args) {
        int[] nums1 = {1, 5};
        int[] nums2 = {2, 5, 9, 10};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] combinSortArr = combineArrays(nums1, nums2);

        if (combinSortArr != null && combinSortArr.length > 0) {
            // 奇数
            boolean oddNum = combinSortArr.length % 2 == 0 ? false : true;

            int mediaIndex = combinSortArr.length / 2;
            if (oddNum) {
                return combinSortArr[mediaIndex];
            }else {
                return (combinSortArr[mediaIndex] + combinSortArr[mediaIndex - 1]) / 2;
            }
        }

        return 0;
    }

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
