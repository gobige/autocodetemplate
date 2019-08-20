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
 *    思路1：这是一个类似于的10进制转26进制的算法，但是又有区别,因为所有都是从A开始到Z，分别求商 求余数
 */
public class L168ExcelTableColumnName {
    public static void main(String[] args) {
        System.out.println(new L168ExcelTableColumnName().convertToTitle(1));
        System.out.println(new L168ExcelTableColumnName().convertToTitle(26));
        System.out.println(new L168ExcelTableColumnName().convertToTitle(53));
    }

    public  String convertToTitle(int n) {
        int quotient = n / 26;
        int residue = n % 26;

        if (quotient == 0) {
            if (residue == 0) {
                return "";
            } else if (residue > 0) {
                return intToLetter(residue) + "";
            }
        } else if (quotient > 0) {
            if (residue == 0) {
                return convertToTitle(quotient - 1) + "Z";
            } else if (residue > 0) {
                return convertToTitle(quotient) + intToLetter(residue);
            }
        }

        return "";
    }

    public char intToLetter(int n) {
        return (char) (64 + n);
    }

}
