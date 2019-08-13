package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 思路：每一行除第一个和最后一个数字为1，其他都为上一行的 对应位置数字 与 对应位置-1数字 之后
 */
public class L119yangHuiTriangle2 {
    public static void main(String[] args) {
        getRow(5);
    }

    public static List<Integer> getRow(int rowIndex) {
        rowIndex = rowIndex + 1;
        if (rowIndex < 1) {
            return new ArrayList<>(0);
        }

        int i = 1;
        Integer[] beforeRows = null;
        while (i <= rowIndex) {
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
            i++;
        }

        return Arrays.asList(beforeRows);
    }


}
