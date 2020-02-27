package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 */
public class L16TheClosestSumOfThreeNumbers {

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int initSum = nums[0] + nums[1] + nums[2];

        int minDisTance;
        if (initSum < target) {
            minDisTance = Math.abs(target - initSum);
        } else if (initSum > target) {
            minDisTance = Math.abs(initSum - target);
        }else {
            return initSum;
        }

        List<Integer> closeList = Arrays.asList(nums[0], nums[1], nums[2]);

        for (int baseNum = 0; baseNum < nums.length; baseNum++) {
            int left = baseNum + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[baseNum];

                int distance;
                if (sum < target) {
                    distance = Math.abs(target - sum);
                    if (distance < minDisTance) {
                        minDisTance = distance;
                        closeList = Arrays.asList(nums[baseNum], nums[left] , nums[right]);
                    }
                    left++;

                } else if (sum > target) {
                    distance = Math.abs(sum - target);
                    if (distance < minDisTance) {
                        minDisTance = distance;
                        closeList = Arrays.asList(nums[baseNum], nums[left] , nums[right]);
                    }
                    right--;
                }else {
                    return sum;
                }
            }
        }

        return closeList.get(0) +closeList.get(1) +closeList.get(2);
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
