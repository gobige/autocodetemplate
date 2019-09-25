package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 */
public class L509FibonacciNumber {
    public int fib(int N) {
        if (N == 1) {
            return 1;
        }
        if (N == 0) {
            return 0;
        } else {
            return fib(N - 1) + fib(N - 2);
        }
    }

}
