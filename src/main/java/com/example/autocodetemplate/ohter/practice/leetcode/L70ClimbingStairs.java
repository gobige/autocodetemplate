package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 思路1 暴力破解  每一步都可能是1或者2
 * 时间复杂度 2^n ,、
 * 空间复杂度 n
 *
 * 思路2 动态规划
 *
 */
public class L70ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(44));
    }
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
