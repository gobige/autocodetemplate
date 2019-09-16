package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.BitSet;

/**
 * 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 思路1 bitset实现
 * 时间复杂度 N 空间复杂度n
 */
public class L268MissingNumber {
    public int missingNumber(int[] nums) {
        BitSet bitSet = new BitSet();

        int max = 0;
        for (int num : nums) {
            bitSet.set(num);
            max = num > max ? num : max;
        }

        for (int i = 0; i < max; i++) {
            if (bitSet.get(i) == false) {
                return i;
            }
        }

        return max+1;
    }
}
