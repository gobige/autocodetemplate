package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 思路1：深度优先遍历
 */
public class L79WordSearch {

    public static void main(String[] args) {

        L79WordSearch word = new L79WordSearch();

        char[][] board = new char[3][4];;
        board[0][0] = 'A';
        board[0][1] = 'B';
        board[0][2] = 'C';
        board[0][3] = 'E';
        board[1][0] = 'S';
        board[1][1] = 'F';
        board[1][2] = 'C';
        board[1][3] = 'S';
        board[2][0] = 'A';
        board[2][1] = 'D';
        board[2][2] = 'E';
        board[2][3] = 'E';

        System.out.println(word.exist(board, "SEE"));
    }


    public boolean exist(char[][] board, String word) {
        // 先找到第一个字符
        int row = board.length;
        int col = board[0].length;

        char firstChar = word.charAt(0);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (firstChar == board[i][j]) {
                    if (deverse(board, i, j, word, 1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean deverse(char[][] board, int row,int col,String word,int charIndex) {
        // 判断是否到末尾
        if (word.length() == charIndex) {
            return true;
        }

        char cmpChar = word.charAt(charIndex);

        if (row - 1 >= 0 && board[row - 1][col] == cmpChar) {
            char temp = board[row - 1][col];
            board[row - 1][col] = '0';
            boolean pass = deverse(board, row - 1, col, word, ++charIndex);
            if (pass) {
                return true;
            }
            // 回滚
            --charIndex;
            board[row - 1][col] = temp;
        } else if (row + 1 < board.length && board[row + 1][col] == cmpChar) {
            char temp = board[row + 1][col];
            board[row + 1][col] = '0';
            boolean pass =  deverse(board, row + 1, col, word, ++charIndex);
            if (pass) {
                return true;
            }
            // 回滚
            --charIndex;
            board[row + 1][col] = temp;
        } else if (col - 1 >= 0 && board[row][col - 1] == cmpChar) {
            char temp = board[row][col + 1];
            board[row][col - 1] = '0';
            boolean pass = deverse(board, row, col - 1, word, ++charIndex);
            if (pass) {
                return true;
            }
            // 回滚
            --charIndex;
            board[row][col - 1] = temp;
        } else if (col + 1 < board[0].length && board[row][col + 1] == cmpChar) {
            char temp = board[row][col + 1];
            board[row][col + 1] = '0';
            boolean pass = deverse(board, row, col + 1, word, ++charIndex);
            if (pass) {
                return true;
            }
            // 回滚
            --charIndex;
            board[row][col + 1] = temp;
        }

        return false;
    }
}
