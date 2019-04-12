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
        twoSum2(arrays, target);
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] returnArrays = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int total = nums[i] + nums[j];
                if (total == target) {
                    returnArrays[0] = i;
                    returnArrays[1] = j;
                    break;
                }
            }
        }

        return returnArrays;
    }


    public static int[] twoSum(int[] nums, int target) {
        int max = getMaxNum(nums);

        int[] calNums = generateCalNums(nums, max);
        if (calNums == null) {
            return null;
        }

        if (2 * max < target) {
            return new int[0];
        }

        int rangeMin = 0;
        int rangeMax = max;
        if (max < target) {
            rangeMin = target - max;
        }
        if (max > target) {
            rangeMax = target;
        }

        int[] result = null;
        while (true) {
            if (rangeMin > rangeMax) {
                break;
            }
            if (calNums[rangeMin] != -1 && calNums[rangeMax] != -1) {
                result = new int[2];
                result[0] = calNums[rangeMin];
                result[1] = calNums[rangeMax];
                break;
            }
            rangeMin++;
            rangeMax--;
        }


        return result;
    }

    private static int[] generateCalNums(int[] nums,int max) {

        if(nums != null && nums.length > 0) {
            int[] calNums = new int[max+1];
            for (int j = 0; j < calNums.length; j++) {
                calNums[j] = -1;
            }
            for (int i = 0; i < nums.length; i++) {
                calNums[nums[i]] = i;
            }

            return calNums;
        }

        return null;
    }

    private static int getMaxNum(int[] nums) {
        int max = 0;
        if(nums != null && nums.length > 0) {
            max = nums[0];
            for(int num : nums) {
                if(num > max) {
                    max = num;
                }
            }
        }

        return max;
    }


}
