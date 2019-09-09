package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 是否是3的幂次方
 *
 * 思路1 不断除以3 是否最后的得到1 特殊情况排除 0,1
 * 思路2 因为 数有限  1162261467 除以N是除尽
 */
public class L326PowerOf3 {

    public boolean isPowerOfThree1(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n % 3 == 0) {
            n = n / 3;
            if (n == 1) {
                return true;
            }
        }

        return false;
    }
}
