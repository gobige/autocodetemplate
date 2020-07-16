package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 思路：我们都知道从左到右数值的值越小，如果从右到左遍历，遇到比右边小的位置数那么久有机会交换变为下一个最大排列
 *
 * 执行用时 : 2 ms, 在Next Permutation的Java提交中击败了99.44% 的用户
 * 内存消耗 : 36.6 MB, 在Next Permutation的Java提交中击败了89.61% 的用户
 */
public class L31NextArrangement {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1};
        nextPermutation(nums);

        System.out.println(nums);

    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            System.out.println(nums);
        }

        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                index = i;
                break;
            }
        }

        // 当前排列是最大数，重新排列为最小排列
        if (index == -1) {
            exchangeSymmetryNum(nums, 0, nums.length - 1);
        }else {
            // 交换左边比定位点大的位置
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[index - 1] < nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[index - 1];
                    nums[index - 1] = temp;
                    break;
                }
            }

            exchangeSymmetryNum(nums, index, nums.length - 1);
        }
    }

    /**
     * 交换数组中对称的两个数值
     * @param nums
     * @param start
     * @param end
     */
    public static void exchangeSymmetryNum(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
