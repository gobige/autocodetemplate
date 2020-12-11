package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 321. 拼接最大数
 *
 *给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 思路1. 双指针，每轮从数组中获取最大的数字，
 * 判断两个指针后面的数字个数是否还够拼接成新数组，不能则重新获取，能则进行下一轮
 *
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 *
 * 思路2，合并排序，从大到小；然后判断每个值是否有效；
 */
public class L321SpliceMaxNum {
    public static void main(String[] args) {

    }


    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        HashMap hashMap = new HashMap();
        for (int i : nums1) {
            hashMap.put(i, 1);
        }

        return null;
    }

//    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        int index1 = 0;
//        int index2 = 0;
//
//        int max = 0;
//
//        while (true) {
//            int maxind1 = getMaxIndex(nums1, index1);
//            int maxind2 = getMaxIndex(nums2, index2);
//        }
//    }

    private int getMaxIndex(int[] nums1, int begin) {
        int max = 0;
        int index = begin;
        for (int i = begin; i < nums1.length; i++) {
            if (nums1[i] > max) {
                index = i;
            }
        }

        return index;
    }
}
