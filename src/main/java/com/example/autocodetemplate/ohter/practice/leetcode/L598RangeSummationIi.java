package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 598. 范围求和 II
 *
 * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
 * 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。
 * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
 *
 * 思路1 暴力求解 依次对举证进行每次操作，最后计算最大数值
 * 思路2 根据题意，ops操作 0,0至少都会涉及，我们只需要求所有ops中的交集即可
 *
 */
public class L598RangeSummationIi {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }

        int rowMin = ops[0][0];
        int colMin = ops[0][1];
        for (int[] op : ops) {
            rowMin = op[0] < rowMin ? op[0] : rowMin;
            colMin = op[1] < colMin ? op[1] : colMin;
        }

        return rowMin * colMin;
    }
}
