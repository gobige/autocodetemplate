package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 231. 2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 思路1：不断用2整除，或如果最终除尽，商为1 则是2的幂次方
 */
public class L231PowerOf2 {

    public boolean isPowerOfTwo(int n) {
        boolean pow = false;
        while (n > 0) {
            if (n == 1) {
                pow = true;
                break;
            } else {
                // 能除尽
                if (n % 2 != 0) {
                    pow = false;
                    break;
                }
                n = n / 2;
            }
        }

        return pow;
    }

}
