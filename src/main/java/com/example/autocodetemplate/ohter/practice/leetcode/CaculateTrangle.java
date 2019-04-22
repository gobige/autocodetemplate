package com.example.autocodetemplate.ohter.practice.leetcode;

import sun.applet.Main;

/**
 * 计算长方形最大面积
 *
 * 盛最多水的容器  给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 思路 动态规划 通过观察 发现从两边向内靠近，越高的即存在可能面积大于当前两边乘积
 */
public class CaculateTrangle {

    public static void main(String[] args) {
        int[] testArr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(testArr));
    }
    public static int maxArea(int[] height) {
        int lIndex = 0;
        int rIndex = height.length - 1;

        int maxArea = 0;

        while (lIndex < rIndex) {
            maxArea = Math.max(maxArea, (rIndex - lIndex) * Math.min(height[lIndex], height[rIndex]));

            if (height[lIndex] < height[rIndex]) {
                lIndex++;
            }else {
                rIndex--;
            }
        }

        return maxArea;
    }
}
