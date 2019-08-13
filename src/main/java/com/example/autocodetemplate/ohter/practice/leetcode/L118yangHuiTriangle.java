package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 思路：每一行除第一个和最后一个数字为1，其他都为上一行的 对应位置数字 与 对应位置-1数字 之后
 */
public class L118yangHuiTriangle {
    public static void main(String[] args) {
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>(0);
        }

        int i = 1;
        Integer[] beforeRows = null;
        List<List<Integer>> triangles = new ArrayList<>(numRows);
        while (i <= numRows) {
            Integer[] rows = new Integer[i];
            // 每一行除第一个和最后一个数字为1
            rows[0] = 1;
            rows[i - 1] = 1;
            if (beforeRows != null) {
                for (int j = 1; j < i - 1; j++) {
                    rows[j] = beforeRows[j] + beforeRows[j - 1];
                }
            }

            beforeRows = rows;
            triangles.add(Arrays.asList(rows));

            i++;
        }

        return triangles;
    }
//    public static List<List<Integer>> generate(int numRows) {
//        if (numRows < 1) {
//            return null;
//        }
//
//        int i = 1;
//        Integer[] beforeRows = null;
//        List<Integer[]> triangles = new ArrayList<>(numRows);
//        while (i <= numRows) {
//            Integer[] rows = new Integer[i];
//            // 每一行除第一个和最后一个数字为1
//            rows[0] = 1;
//            rows[i - 1] = 1;
//            if (beforeRows != null) {
//                for (int j = 1; j < i - 1; j++) {
//                    rows[j] = beforeRows[j] + beforeRows[j - 1];
//                }
//            }
//
//            beforeRows = rows;
//            triangles.add(rows);
//
//            i++;
//        }
//
//        return triangles;
//    }


}
