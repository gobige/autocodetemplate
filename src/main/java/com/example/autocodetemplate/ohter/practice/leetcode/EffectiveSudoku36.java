package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 思路：按照规则验证，使用hash验证是否存在
 */
public class EffectiveSudoku36 {

    public static void main(String[] args) {

        EffectiveSudoku36 s = new EffectiveSudoku36();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        s.isValidSudoku(board);

    }

    public  boolean isValidSudoku(char[][] board) {

        BitSet bitSet = new BitSet();

        // 横验证
        for (char[] rows : board) {

            bitSet.clear();
            for (char row : rows) {
                if (row == '.') {
                    continue;
                }

                int num = Integer.parseInt("" + row);
                // 如果已经存在 不通过规则验证
                if (bitSet.get(num)) {
                    return false;
                }else {
                    bitSet.set(num);
                }
            }
        }

        // 竖验证
        for (int col = 0; col < board[0].length; col++) {

            bitSet.clear();
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == '.') {
                    continue;
                }

                int num = Integer.parseInt("" + board[row][col]);
                // 如果已经存在 不通过规则验证
                if (bitSet.get(num)) {
                    return false;
                } else {
                    bitSet.set(num);
                }
            }
        }

        // 3X3验证

        List<rowColObj> block = new LinkedList<rowColObj>();
        for (int i = 0; i * 3 < 9; i++) {
            for (int j = 0; j * 3 < 9; j++) {
                rowColObj obj = new rowColObj(i * 3, j * 3);
                block.add(obj);
            }
        }


        for(rowColObj obj : block) {
            int startRow = obj.getRow();
            int startCol = obj.getCol();;
            bitSet.clear();
            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    if (board[row][col] == '.') {
                        continue;
                    }

                    int num = Integer.parseInt("" + board[row][col]);
                    // 如果已经存在 不通过规则验证
                    if (bitSet.get(num)) {
                        return false;
                    }else {
                        bitSet.set(num);
                    }
                }
            }
        }

        return true;
    }

    class rowColObj {

        rowColObj(int row, int col) {
            this.row = row;
            this.col = col;
        }
        int row;
        int col;

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }

}
