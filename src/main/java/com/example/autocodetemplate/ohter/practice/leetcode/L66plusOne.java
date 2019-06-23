package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 思路 模拟十进制进位机制
 *
 *执行用时 : 1 ms , 在所有 Java 提交中击败了 96.75% 的用户
 * 内存消耗 : 36.1 MB , 在所有 Java 提交中击败了 36.58% 的用户
 */
public class L66plusOne {

    public static void main(String[] args) {
        plusOne(new int[]{9});
    }

    public static int[] plusOne(int[] digits) {
        int length = digits.length;

        boolean carry = true;
        for (int digt = length - 1; digt >= 0; digt--) {
            if (carry) {
                if (digits[digt] + 1 > 9) {
                    carry = true;
                    digits[digt] = 0;
                }else {
                    carry = false;
                    digits[digt] = digits[digt] + 1;
                }
            }else {
                break;
            }
        }

        if (carry) {
            int[] newdigits = new int[digits.length + 1];
            for (int i = 1; i < newdigits.length; i++) {
                newdigits[i] = digits[i-1];
            }
            digits = newdigits;
            digits[0] = 1;
        }

        return digits;
    }
}
