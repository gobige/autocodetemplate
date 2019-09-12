package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 *
 * 注意:
 * n 是正数且在32为整形范围内 ( n < 231)。
 *
 * 思路1：
 *
 */
public class L400NthNumber {
    public static void main(String[] args) {
        System.out.println(findNthDigit(11));
    }
    public static int findNthDigit(int n) {
        int i = 0;
        int num = 0;

        while (true) {
            char[] intChars = String.valueOf(num).toCharArray();
            for (char c : intChars) {
                if (i == n) {
                    return Integer.valueOf(String.valueOf(c));
                }
                i++;
            }
            num++;
        }
    }
}
