package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class SumTest {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * Example:
     * <p>
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */

    public static void main(String[] args) {
        int[] arrays = {1, 3, 5, 7, 11};
        int target = 14;
        int[] returnArr = new SumTest().getIndexBySum(arrays, target);

        for (int i = 0; i < returnArr.length; i++) {
            System.out.println(returnArr[i] + ",");
        }

    }

    public int[] getIndexBySum(int[] arrays, int target) {
        int[] returnArrays = new int[2];
        for (int i = 0; i < arrays.length; i++) {
            if (i == arrays.length - 1) {
                break;
            }
            for (int j = i + 1; j < arrays.length; j++) {
                int total = arrays[i] + arrays[j];
                if (total == target) {
                    returnArrays[0] = i;
                    returnArrays[1] = j;
                    break;
                }
            }
        }

        return returnArrays;
    }
}
