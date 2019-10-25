package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 657. 机器人能否返回原点
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 *
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 *
 * 思路1 遍历字符串，修改，确定方向矢量，最后判断矢量是否都为0
 */
public class L657RobotReturnToTheOrigin  {

    public boolean judgeCircle(String moves) {
        int row = 0;
        int col = 0;

        char[] chars = moves.toCharArray();

        for (char aChar : chars) {
            switch (aChar) {
                case 'R': row++;
                    break;
                case 'L': row--;
                    break;
                case 'U': col++;
                    break;
                case 'D': col--;
                    break;
                default:
                    return false;
            }
        }

        if (row == 0 && col == 0) {
            return true;
        }else {
            return false;
        }
    }
}
