package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 867. 转置矩阵
 *
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 */
public class L867TransposeMatrix {
    public int[][] transpose(int[][] matrix) {

        int length = matrix.length;
        int length2 = matrix[0].length;
        int row = length;
        int col = matrix[0].length;
        int[][] trans = new int[col][row];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length2; j++) {
                trans[j][i] = matrix[i][j];
            }
        }

        return trans;
    }
}
