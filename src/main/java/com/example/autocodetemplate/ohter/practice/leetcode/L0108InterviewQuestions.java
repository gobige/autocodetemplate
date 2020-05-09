package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 面试题 01.08. 零矩阵  编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 求并集
 *
 * 思路
 */
public class L0108InterviewQuestions {
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

        setZeroes(matrix);
     }



    public static void setZeroes(int[][] matrix) {
        Set<String> set = new HashSet<>();
        int M = matrix.length;
        for (int i = 0; i < M; i++) {

            int N = matrix[0].length;
            for (int j = 0; j < N; j++) {

                if (matrix[i][j] == 0) {
                    // 列清零
                    for (int col = 0; col < N; col++) {
                        set.add(i+"-"+col);
                    }
                    // 行清零
                    for (int row = 0; row < M; row++) {
                        set.add(row+"-"+j);
                    }
                }
            }
        }

        // 清零
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            String[] strs = s.split("-");
            matrix[Integer.valueOf(strs[0])][Integer.valueOf(strs[1])] = 0;
        }
    }


}



