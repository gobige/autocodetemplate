package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 645. 错误的集合
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 思路 bitset 先找出重复的，再找出缺失的
 * 时间复杂度 2n 空间复杂度 n
 *
 * 思路2 求和，求重复的数同时进行
 */
public class L645WrongCollection {

    public static void main(String[] args) {
        findErrorNums(new int[]{1, 2, 2, 4 });
    }

    public static int[] findErrorNums(int[] nums) {
        BitSet bitSet = new BitSet(10000);

        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (bitSet.get(nums[i])) {
                res[0] = nums[i];
            }else {
                bitSet.set(nums[i]);
            }
        }

        for (int i = 1; i < nums.length+1; i++) {
            if (!bitSet.get(i)) {
                res[1] = i;
                break;
            }
        }

        return res;
    }
}
