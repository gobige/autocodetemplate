package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 思路1：根据题意，三个数的组合有两种，【1】两个负数，一个正数 【2】三个正数
 * 先分为正数，负数两组
 */
public class L628TheLargestProductOfThreeNumbers {
    public static void main(String[] args) {
        maximumProduct(new int[]{1, 2, 3, 4});
    }
    public static int maximumProduct(int[] nums) {

        quickSort(nums, 0, nums.length - 1);

        int neg2posi1comb = nums[0] * nums[1] * nums[nums.length - 1];
        int posi3comb = nums[nums.length - 3] * nums[nums.length - 2] *  nums[nums.length - 1];

        return neg2posi1comb > posi3comb ? neg2posi1comb : posi3comb;
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
