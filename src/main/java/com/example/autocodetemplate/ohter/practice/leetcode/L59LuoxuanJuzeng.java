package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 59. 螺旋矩阵 II
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 思路1：找规律，遇到越界换方向;整个螺旋先后顺序是 先col+, row+, col-，row-；真个螺旋方向状态改变事件event，越界和数组已有占位，
 * 让我想起了贪吃蛇
 *
 * 找数学的公式，有点难
 *
 */
public class L59LuoxuanJuzeng {

    public static void main(String[] args) {

        generateMatrix(4);
    }


    public static int[][] generateMatrix(int n) {
        if (n == 1) {
            int[][] retArr = new int[1][1];
            retArr[0][0] = 1;
            return retArr;
        }

        int num = n * n;

        int[][] arrays = new int[n][n];

        int curRow = 0;
        int curCol = 0;

        StatusChangeEnum status = StatusChangeEnum.nextStatus(null);
        for (int i = 1; i < num + 1; i++) {
            arrays[curRow][curCol] = i;

            // 方向自增
            if (StatusChangeEnum.COL_PLUS == status) {
                // 数组越界,有占位情况，掉转方向
                if (curCol + 1 > n - 1 || arrays[curRow][curCol + 1] != 0) {
                    status = StatusChangeEnum.ROW_PLUS;

                    // 调整方向后自增
                    curRow++;
                } else {
                    curCol++;
                }
            } else if (StatusChangeEnum.ROW_PLUS == status) {
                if (curRow + 1 > n - 1 || arrays[curRow + 1][curCol] != 0) {
                    status = StatusChangeEnum.COL_MINUS;
                    curCol--;
                } else {
                    curRow++;
                }
            } else if (StatusChangeEnum.COL_MINUS == status) {
                if (curCol - 1 < 0 || arrays[curRow][curCol - 1] != 0) {
                    status = StatusChangeEnum.ROW_MINUS;
                    curRow--;
                } else {
                    curCol--;
                }
            } else if (StatusChangeEnum.ROW_MINUS == status) {
                if (curRow - 1 < 0 || arrays[curRow - 1][curCol] != 0) {
                    status = StatusChangeEnum.COL_PLUS;
                    curCol++;
                } else {
                    curRow--;
                }
            }
        }

        return arrays;
    }

}

enum StatusChangeEnum {
    COL_PLUS(1, "col+"),
    ROW_PLUS(2, "row+"),
    COL_MINUS(3, "col-"),
    ROW_MINUS(4, "row-");

    private Integer key;
    private String value;

    static StatusChangeEnum nextStatus(StatusChangeEnum statusChangeEnum) {
        if (statusChangeEnum == null) {
            return COL_PLUS;
        }

        StatusChangeEnum nextStatus = null;
        switch (statusChangeEnum) {
            case COL_PLUS:
                nextStatus = ROW_PLUS;
                break;
            case ROW_PLUS:
                nextStatus = COL_MINUS;
                break;
            case COL_MINUS:
                nextStatus = ROW_MINUS;
                break;
            case ROW_MINUS:
                nextStatus = COL_PLUS;
                break;
            default:
                break;
        }


        return nextStatus;
    }

    StatusChangeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}