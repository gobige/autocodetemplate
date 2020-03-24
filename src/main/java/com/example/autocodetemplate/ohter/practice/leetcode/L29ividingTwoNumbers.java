package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * . 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 思路1  那就只有用减法喽  当时碰到被除数很大 除数很小的情况时间复杂度就会变成n，优化：使用倍增除数 例：2,4,8
 * 时间复杂度 dividend/divisor
 *
 */
public class L29ividingTwoNumbers {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 1));
    }

    public  static  int divide(int dividend, int divisor) {
        Long dividendLong = Long.valueOf(dividend);
        Long divisorLong = Long.valueOf(divisor);
        if (dividendLong == 0) {
            return 0;
        }

        boolean minusFlag = false;
        if (dividendLong < 0 && divisorLong > 0) {
            minusFlag = true;
        } else if(dividendLong > 0 && divisorLong < 0){
            minusFlag = true;
        }

        dividendLong = Math.abs(dividendLong);
        divisorLong = Math.abs(divisorLong);

        long i = 0;
        long multip = divisorLong;
        long multipi = 1;
        while (dividendLong >= divisorLong) {
            // 可倍增
            if (dividendLong - multip >= 0) {
                dividendLong = dividendLong - multip;
                i += multipi;
                multip += multip;
                multipi += multipi;
            } else {
                multip = divisorLong;
                multipi = 1;
//                dividendLong = dividendLong - divisorLong;
//                i++;
            }
        }

        i = minusFlag ? -i : i;

        if (Long.valueOf(-2147483648) <= i && i <= Long.valueOf(2147483647)) {
            return Long.valueOf(i).intValue();
        }else {
            return Integer.MAX_VALUE;
        }
    }

}
