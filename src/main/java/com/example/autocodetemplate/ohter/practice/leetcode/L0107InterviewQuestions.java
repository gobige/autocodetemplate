package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 面试题 01.07. 旋转矩阵 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 *
 * 思路 分而治之，根据包围层数进行分别旋转90度
 */
public class L0107InterviewQuestions {
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        matrix[0][0]=1;
        matrix[0][1]=2;
        matrix[0][2]=3;
        matrix[1][0]=4;
        matrix[1][1]=5;
        matrix[1][2]=6;
        matrix[2][0]=7;
        matrix[2][1]=8;
        matrix[2][2]=9;

        rotate(matrix);
     }


    public static void rotate(int[][] matrix) {
        int N = matrix.length;
        // 偶数
        int cycle = N / 2 - 1;

        while (cycle != -1) {
            for (int i = cycle; i < N - cycle - 1; i++) {
                exchange90(cycle, i, matrix, N);
            }
            cycle--;
        }
    }

    private static void exchange90(int row, int col, int[][] matrix,int N) {
        int temp = matrix[col][N - 1 - row];
        matrix[col][N - 1 - row] = matrix[row][col];

        exchange901(col, N - 1 - row, matrix, N, temp,1);
    }
    private static void exchange901(int row, int col, int[][] matrix,int N,int temp,int dept) {
        if (dept > 3) {
            return;
        }
        int temp2 = matrix[col][N - 1 - row];
        matrix[col][N - 1 - row] = temp;

        exchange901(col, N - 1 - row, matrix, N, temp2, ++dept);
    }


}



