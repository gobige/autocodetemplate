package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 414. 第三大的数
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 *
 */
public class L414ThirdLargestNumber {

    public int thirdMax(int[] nums) {

        Integer[] sortArray = new Integer[]{null, null, null};

        for (int num : nums) {
            if (sortArray[0] != null) {
                if (num > sortArray[0]) {
                    sortArray[0] = num + sortArray[0];
                    num = sortArray[0] - num;
                    sortArray[0] = sortArray[0] - num;
                } else if (num == sortArray[0]) {
                    continue;
                }
            } else {
                sortArray[0] = num;
                continue;
            }


            if (sortArray[1] != null) {
                if (num > sortArray[1]) {
                    sortArray[1] = num + sortArray[1];
                    num = sortArray[1] - num;
                    sortArray[1] = sortArray[1] - num;
                } else if (num == sortArray[1]) {
                    continue;
                }
            } else {
                sortArray[1] = num;
                continue;
            }


            if (sortArray[2] != null) {
                if (num > sortArray[2]) {
                    sortArray[2] = num + sortArray[2];
                    num = sortArray[2] - num;
                    sortArray[2] = sortArray[2] - num;
                } else if (num == sortArray[2]) {
                    continue;
                }
            } else {
                sortArray[2] = num;
                continue;
            }
        }

        return sortArray[2] == null ? sortArray[0] : sortArray[2];
    }
}
