package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 441. 排列硬币
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 *
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 *
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 */
public class L441ArrangeCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }
    public static int arrangeCoins(int n) {
        int c = 1;
        long t1 = 0L;
        long t2 = 0L;
        while (true) {
            t1 = (long)2 * n;
            t2 = (long)c * (c + 1);
            if (t1 < t2) {
                return c - 1;
            }
            c++;
        }
    }
}
