package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * Excel表列名称
 *
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *
 *    思路1：这是一个典型的10进制转26进制
 */
public class L168ExcelTableColumnName {
    public static void main(String[] args) {
        System.out.println(new L168ExcelTableColumnName().convertToTitle(52));
    }

    public  String convertToTitle(int n) {
        if (n > 26) {
            return convertToTitle(n / 26) + intToLetter(n % 26);
        }else {
            return intToLetter(n) + "";
        }
    }

    public char intToLetter(int n) {
        if (n == 0) {
            return 'Z';
        }

        return (char) (64 + n);
    }

}
