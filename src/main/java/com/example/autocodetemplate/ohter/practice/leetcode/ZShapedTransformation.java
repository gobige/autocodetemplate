package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * Z 字形变换
 * 思路：先填充进入矩阵，再通过矩阵获取转换后字符串
 */
public class ZShapedTransformation {

    public static void main(String[] args) {

        System.out.println(convert("LEETCODEISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        char[] characters = s.toCharArray();

        // 获取需要排列多少列
        int colNums = getColumNum(characters, numRows);

        char[][] chars = new char[numRows][colNums];

        // 填充字符到矩阵
        chars = getMatrixAfterFillChar(chars, characters, numRows, colNums);

        StringBuilder stringBuilder = new StringBuilder("");
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < colNums; col++) {
                if (chars[row][col] != 0) {
                    stringBuilder.append(chars[row][col]);
                }
            }
        }

        return stringBuilder.toString();
    }

    /**
     *  填充字符到矩阵
     * @param chars
     * @param characters
     * @param numRows
     * @param colNums
     * @return
     */
    public static char[][] getMatrixAfterFillChar(char[][] chars, char[] characters, int numRows, int colNums) {
        int get = 0;
        // 字符插入矩阵
        for (int col = 0; col < colNums; col++) {
            for (int row = 0; row < numRows; row++) {
                // 填充完成，返回填充后矩阵
                if (get == characters.length) {
                    return chars;
                }

                // 首行，尾行，双数列不填充值
                if ((row == 0 || row == numRows - 1) && col % 2 != 0) {

                } else {
                    chars[row][col] = characters[get];
                    get++;
                }
            }
        }

        return chars;
    }

    /**
     * 获取需要排列多少列
     * @param characters
     * @param numRows
     * @return
     */
    public static int getColumNum(char[] characters, int numRows) {
        if (numRows > 2) {
            int ModularParam = numRows * 2 - 2;

            int column = characters.length / ModularParam;
            int modular = characters.length % ModularParam;

            // 分配列 可多不可少
            if (modular > numRows) {
                return (numRows) * column;
            }else {
                return column*2 + 1;
            }
        }

        return characters.length / numRows + 1;
    }

}
