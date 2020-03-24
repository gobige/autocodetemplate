package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 492. 构造矩形
 *
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，
 * 你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 *
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 *
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 *
 * 思路1 从1和本身相乘开始 ，使用双指针获取成立的组合
 *
 */
public class L492ConstructingARectangle {
    public static void main(String[] args) {
        System.out.println(constructRectangle(4));

    }
    public static int[] constructRectangle(int area) {
        int L = area;
        int W = 1;

        int left = area;
        int right = 1;

        right++;
        while (left >= right) {
            if (area == left * right) {
                L = left;
                W = right;
                right++;
            } else if (area < left * right) {
                left--;
            } else if (area > left * right) {
                right++;
            }
        }

        return new int[]{L, W};
    }
}
