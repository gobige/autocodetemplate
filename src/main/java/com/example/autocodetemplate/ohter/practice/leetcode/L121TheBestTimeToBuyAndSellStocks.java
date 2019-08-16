package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 思路1：先从小到大排序  使用双指针遍历  判断原数组左指针数值是否在右指针左边， 得到错误{4,1,2}
 *
 * 思路2：转换为求最大值和最小值的问题，最大连续数组问题
 */
public class L121TheBestTimeToBuyAndSellStocks {

    public static void main(String[] args) {
        L121TheBestTimeToBuyAndSellStocks s = new L121TheBestTimeToBuyAndSellStocks();

        System.out.println(s.maxProfit(new int[]{4,1,2}));

    }

    public  int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minv = Integer.MAX_VALUE;
        int maxDiff = 0;
        for (int price : prices) {
            if (price < minv) {
                minv = price;
            }else {
                if (price - minv > maxDiff) {
                    maxDiff = price - minv;
                }
            }
        }

        return maxDiff;
    }

    public  int maxProfit1(int[] prices) {
        int i = 0;
        DayPrice[] dayPrices = new DayPrice[prices.length];
        for (int price : prices) {
            DayPrice dayPrice = new DayPrice();
            dayPrice.setIndex(i);
            dayPrice.setPrice(price);

            dayPrices[i] = dayPrice;
            i++;
        }

        Arrays.sort(dayPrices);

        int left = 0;
        int right = dayPrices.length - 1;
        while (left < right) {
            if (dayPrices[right].getIndex() > dayPrices[left].getIndex()) {
                return dayPrices[right].getPrice() - dayPrices[left].getPrice();
            }

            int rightDiff = dayPrices[right].getPrice() - dayPrices[right - 1].getPrice();
            int leftDiff = dayPrices[left + 1].getPrice() - dayPrices[left].getPrice();
            if (rightDiff < leftDiff) {
                right--;
            }else {
                left++;
            }
        }

        return 0;
    }

    class DayPrice implements Comparable<DayPrice> {
        int index;
        int price;


        @Override
        public int compareTo(DayPrice o) {
            if (this.getPrice() < o.getPrice()) {
                return -1;
            }else   if (this.getPrice() > o.getPrice()) {
                return  1;
            }

            return 0;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

}
