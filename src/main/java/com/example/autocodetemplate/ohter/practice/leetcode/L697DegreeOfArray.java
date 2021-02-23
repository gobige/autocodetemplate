package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 思路1：hashMap扩展数据结构
 */
public class L697DegreeOfArray {
    public static void main(String[] args) {
        findShortestSubArray(new int[]{1, 2, 2, 3, 1});
    }
    public static int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        // 分别记录每个数字出现的次数和距离
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (hashMap.containsKey(num)) {
                int[] arr = hashMap.get(num);
                arr[0] = arr[0] + 1;
                arr[2] = i;
                hashMap.put(num, arr);
            } else {
                int[] arr =new int[3];
                arr[0] = 1;
                arr[1] = i;
                arr[2] = i;
                hashMap.put(num, arr);
            }
        }

        int maxNum = 0, minLine = 0;
        for (Map.Entry<Integer, int[]> entry : hashMap.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLine = arr[2] - arr[1];
            } else if (maxNum == arr[0]) {
                if (minLine > arr[2] - arr[1]) {
                    minLine = arr[2] - arr[1];
                }
            }
        }

        return minLine + 1;
    }

}
