package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 566. 重塑矩阵
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 */
public class L566RemodelingMatrix {
    public static void main(String[] args) {
        matrixReshape(new int[][]{new int[]{1, 2}, new int[]{3, 4}}, 1, 4);
    }
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int MatrixLenth = nums.length * nums[0].length;
        if (MatrixLenth == r * c) {
            int newRow = 0;
            int newCol = 0;
            int[][] newNums = new int[r][c];
            for (int[] rows : nums) {
                for (int col : rows) {
                    if (newCol == c) {
                        newRow++;
                        newCol = 0;
                    }
                    newNums[newRow][newCol] = col;
                    newCol++;
                }
            }

            return newNums;
        }else {
            return nums;
        }
    }
}
