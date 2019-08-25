package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 思路1 动态规划
 * f(k) = 从前 k 个房屋中能抢劫到的最大数额，A_iA
 * i = 第 i 个房屋的钱数。
 *
 * Max(f(k - 2) + Ak,f(k - 1))
 */
public class L198FightHome {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 3, 5, 2, 1, 6}));

    }

    public static int rob(int[] nums) {
        int preMax = 0;
        int curMax = 0;

        for (int n : nums) {
            int temp = curMax;
            curMax = Math.max(preMax + n, curMax);
            preMax = temp;
        }

        return curMax;
    }


}
