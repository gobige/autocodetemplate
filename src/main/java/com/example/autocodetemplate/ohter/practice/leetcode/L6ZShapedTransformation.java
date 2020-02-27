package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Z 字形变换
 * 思路：先填充进入矩阵，再通过矩阵获取转换后字符串 空间时间复杂度高)
 * 思路2：桶排序的思路)
 */
public class L6ZShapedTransformation {

    public static void main(String[] args) {

        System.out.println(convert("LEETCODEISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        char[] characters = s.toCharArray();

        LinkedList<Character>[] bukets = new LinkedList[numRows];

        // 入桶
        int get = 0;
        int bucketIndex = 0;
        boolean reverse = false;

        // 第一次入桶特殊处理，避免反转
        LinkedList<Character> buketList = bukets[bucketIndex];
        if (buketList == null) {
            buketList = new LinkedList();
            bukets[bucketIndex] = buketList;
        }
        buketList.addLast(characters[get]);
        bucketIndex++;
        get++;

        while (get < characters.length) {
            buketList = bukets[bucketIndex];

            if (buketList == null) {
                buketList = new LinkedList();
                bukets[bucketIndex] = buketList;
            }
            buketList.addLast(characters[get]);

            if (bucketIndex == numRows - 1) {
                reverse = true;
            } else if (bucketIndex == 0) {
                reverse = false;
            }

            if (reverse) {
                bucketIndex--;
            }else {
                bucketIndex++;
            }
            get++;
        }

        // 从桶中取出数据
        StringBuilder stringBuilder = new StringBuilder("");

        for (LinkedList linkedList : bukets) {
            if (linkedList != null) {
                for (Object o : linkedList) {
                    stringBuilder.append((Character) o);
                }
            }
        }

        return stringBuilder.toString();
    }


    public static String convert2(String s, int numRows) {
        char[] characters = s.toCharArray();

        if (s == null || s.equals("")) {
            return "";
        }
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // 获取需要排列多少列
        int colNums = getColumNum(characters, numRows);

        char[][] chars = new char[numRows][colNums];

        // 填充字符到矩阵
        chars = getMatrixAfterFillChar(chars, characters, numRows, colNums);

        // 从矩阵按规则取出数据
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

        int col = 0;
        int row = 0;

        vertical(get, characters, chars, numRows, row, col);

        return chars;
    }

    /**
     * 竖方向数据填充
     * @param get
     * @param characters
     * @param chars
     * @param numRows
     * @param row
     * @param col
     */
    public static void vertical(int get, char[] characters, char[][] chars, int numRows, int row, int col) {
        if (get == characters.length) {
            return;
        }

        while (row < numRows) {
            if (get == characters.length) {
                return;
            }

            chars[row][col] = characters[get];
            row++;
            get++;
        }

        oblique(get, characters, chars, numRows, row - 2, col + 1);
    }

    /**
     * 斜方向数据填充
     * @param get
     * @param characters
     * @param chars
     * @param numRows
     * @param row
     * @param col
     */
    public static void oblique(int get, char[] characters, char[][] chars, int numRows, int row, int col) {
        if (get == characters.length) {
            return;
        }

        while (row > -1) {
            if (get == characters.length) {
                return;
            }

            chars[row][col] = characters[get];
            row--;
            col++;
            get++;
        }

        vertical(get, characters, chars, numRows, row + 2, col - 1);
    }

    /**
     * 获取需要排列多少列
     * @param characters
     * @param numRows
     * @return
     */
    public static int getColumNum(char[] characters, int numRows) {
        int ModularParam = numRows * 2 - 2;

        int column = characters.length / ModularParam;

        return numRows * (column + 1);
    }
}
