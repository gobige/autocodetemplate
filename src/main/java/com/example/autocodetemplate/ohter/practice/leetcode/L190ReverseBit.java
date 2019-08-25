package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 190. 颠倒二进制位
 *
 * 颠倒给定的 32 位无符号整数的二进制位。
 */
public class L190ReverseBit {
    public int reverseBits(int n) {
        String  binaryStr =  String.valueOf(n);

        char[] chars = binaryStr.toCharArray();

        rotate(chars, 0, chars.length - 1);

        return Integer.valueOf(chars.toString());
    }

    private static void rotate(char[] nums,int left,int right) {
        char temp;
        while (left < right) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
