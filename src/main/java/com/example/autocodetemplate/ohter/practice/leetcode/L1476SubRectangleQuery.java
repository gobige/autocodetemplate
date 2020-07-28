package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 1476. 子矩形查询
 * 请你实现一个类 SubrectangleQueries ，它的构造函数的参数是一个 rows x cols 的矩形（这里用整数矩阵表示），并支持以下两种操作：
 *
 * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
 * 用 newValue 更新以 (row1,col1) 为左上角且以 (row2,col2) 为右下角的子矩形。
 *
 * 2. getValue(int row, int col)
 * 返回矩形中坐标 (row,col) 的当前值。
 *
 */
public class L1476SubRectangleQuery {

    private int[][] rectangle;
    private int row;
    private int col;

    public static void main(String[] args) {

    }

    public L1476SubRectangleQuery(int[][] rectangle) {
        this.rectangle = rectangle;
        row = rectangle.length;
        if (row > 0) {
            col = rectangle[0].length;
        }
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        if (row1 > row2 || col1 > col2) {
            return;
        }
        if (row2 > this.row) {
            return;
        }
        if (col2 > this.col) {
            return;
        }
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        if (row > this.row) {
            return 0;
        }
        if (col > this.col) {
            return 0;
        }

        return rectangle[row][col];
    }


    public int[][] getRectangle() {
        return rectangle;
    }

    public void setRectangle(int[][] rectangle) {
        this.rectangle = rectangle;
    }

}
