package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 807. 保持城市天际线
 *
 * 在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
 *
 * 最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
 *
 * 建筑物高度可以增加的最大总和是多少？
 *
 * 思路1：遍历，每个值横向，竖向观看都不能大于最大数，使用MIN()公式进行计算
 *
 */
public class L807KeepTheCitySkyline {

    public static void main(String[] args) {
        L807KeepTheCitySkyline l = new L807KeepTheCitySkyline();
        int[][] grid = new int[4][4];
        grid[0][0] = 3;
        grid[0][1] = 0;
        grid[0][2] = 8;
        grid[0][3] = 4;
        grid[1][0] = 2;
        grid[1][1] = 4;
        grid[1][2] = 5;
        grid[1][3] = 7;
        grid[2][0] = 9;
        grid[2][1] = 2;
        grid[2][2] = 6;
        grid[2][3] = 3;
        grid[3][0] = 0;
        grid[3][1] = 3;
        grid[3][2] = 1;
        grid[3][3] = 0;

        l.maxIncreaseKeepingSkyline(grid);
    }


    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        // 求出横向，纵向的最大值
        int[] rowMaxs = new int[row];
        int[] colMaxs = new int[col];
        for (int i = 0; i < row; i++) {
            int max = 0;
            for (int j = 0; j < col; j++) {
                max = Math.max(grid[i][j], max);
            }
            rowMaxs[i] = max;
        }
        for (int i = 0; i < col; i++) {
            int max = 0;
            for (int j = 0; j < row; j++) {
                max = Math.max(grid[j][i], max);
            }
            colMaxs[i] = max;
        }

        // 求最大可增高度
        int maxAdd = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                maxAdd += Math.min(rowMaxs[i], colMaxs[j]) - grid[i][j];
            }
        }

        return maxAdd;
    }

}
