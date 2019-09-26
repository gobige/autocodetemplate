package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 561. 数组拆分 I
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1),
 * (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 *
 * 通过观察，要是总和最大，min要尽量够大，但是又要有一个比min稍微大的数配对，所以先进行排序
 * 时间复杂度logn 空间复杂度 1
 */
public class L561ArraySplitI {
    public int arrayPairSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

        int min = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            min += nums[i];
        }

        return min;
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arrays, int start, int end) {
        // 如果每一轮基准排序结束后返回
        if(start > end){
            return;
        }

        int base = arrays[start];
        int i = start,j = end;
        int temp;
        // 保证调用不会内存泄露
        while (start < end) {
            // 从最右边开始查找如果比基准书小则结束查找，锁定位置
            while (base <= arrays[end] && start < end) {
                end--;
            }
            // 从最左边开始查找如果比基准数大则结束查找，锁定位置
            while (base >= arrays[start] && start < end) {
                start++;
            }
            // 交换比基准数大的和小的数值位置
            if (start < end) {
                temp = arrays[start];
                arrays[start] = arrays[end];
                arrays[end] = temp;
            }
        }

        // 基准数归位
        arrays[i] = arrays[start];
        arrays[start] = base;

        // 递归遍历
        quickSort(arrays,i,start - 1);
        quickSort(arrays,start + 1,j);
    }
}
