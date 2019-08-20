package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 思路1 字母转数字，
 *
 */
public class L171ExcelTableNumber {
    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));

    }

    public static int titleToNumber(String s) {
        char[] chars = s.toCharArray();

        int power = 0;
        int sum = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            int val = (int) chars[i] - 64;

            sum += val * Math.pow(26, power);

            power++;
        }

        return sum;
    }
}
