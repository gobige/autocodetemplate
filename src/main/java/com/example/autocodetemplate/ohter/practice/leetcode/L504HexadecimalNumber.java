package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 504. 七进制数
 *
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 思路1：十进制转七进制 不断除7拼装余数
 */

public class L504HexadecimalNumber {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean negative = num < 0 ? true : false;

        num = Math.abs(num);
        String s = new String();

        while (num > 6) {
            int divide = num / 7;
            int remainder = num % 7;
            s = remainder + s;
            num = divide;
        }
        if (num > 0) {
            s = num + s;
        }

        if (negative) {
            s = "-" + s;
        }

        return s;
    }
}
