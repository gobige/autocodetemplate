package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 263. 丑数
 * 编写一个程序判断给定的数是否为丑数。
 *
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 * 思路1：
 */
public class L263Ugly {
    public static void main(String[] args) {
         System.out.println(0 % 2);

    }
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }

        while (true) {
            if (num % 2 == 0) {
                num = num / 2;
                if (num == 1) {
                    return true;
                }
            } else if (num % 3 == 0) {
                num = num / 3;
                if (num == 1) {
                    return true;
                }
            } else if (num % 5 == 0) {
                num = num / 5;
                if (num == 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
