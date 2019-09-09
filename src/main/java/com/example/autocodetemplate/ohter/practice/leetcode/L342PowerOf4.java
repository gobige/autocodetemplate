package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 是否是4的幂次方
 * 思路1 不断除以3 是否最后的得到1 特殊情况排除 0,1
 */
public class L342PowerOf4 {

    public boolean isPowerOfFour1(int num) {

        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        while (num % 4 == 0) {
            num = num / 4;
            if (num == 1) {
                return true;
            }
        }

        return false;
    }
}
