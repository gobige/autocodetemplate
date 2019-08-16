package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 思路1：准备两个指针，分别代表买入，卖出，当后面数值大于前面数值是 买入指针插入； 当后面数值小于前面数值时卖出指针插入，计算盈利，一直遍历数组完
 *
 * 时间复杂度 n 空间复杂度 1
 */
public class L122TheBestTimeToBuyAndSellStocks {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));

    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int buyIndex = 0;
        int sellIndex = 0;

        boolean buy = true;
        for (int i = 0; i < prices.length - 1; i++) {
            // 买
            if (buy) {
                if (prices[i + 1] > prices[i]) {
                    buyIndex = i;
                    buy = false;
                }
            }else {
                if (prices[i + 1] < prices[i]) {
                    sellIndex = i;
                    buy = true;

                    profit += prices[sellIndex] - prices[buyIndex];
                }
            }
        }

        // 如果 最后状态是卖 则直接卖出  则略过  是买 则略过
        if (!buy) {
            profit += prices[prices.length - 1] - prices[buyIndex];
        }

        return profit;
    }

}
