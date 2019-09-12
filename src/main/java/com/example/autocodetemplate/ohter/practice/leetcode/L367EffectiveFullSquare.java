package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 思路1 使用二分查找法，对比是否有该数的完全平方数
 * 时间复杂度 log n 空间复杂度 1
 *
 * 思路2 数学定理(1 + 3 + 5 + ... + (2n - 1) = n ^ 2)
 */
public class L367EffectiveFullSquare {

    public static void main(String[] args) {
        isPerfectSquare(2147483647);
    }
    public static boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        int left = 0;
        int right = num;
        int index = 0;
        while (left <= right) {
            index = (left + right) / 2;
            if ((double)index * index > num) {
                right = index - 1;
            } else if (index * index < num) {
                left = index + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
