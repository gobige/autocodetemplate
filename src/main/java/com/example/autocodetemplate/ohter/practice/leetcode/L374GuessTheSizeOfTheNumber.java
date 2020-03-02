package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 374. 猜数字大小
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * -1 : 我的数字比较小
 *  1 : 我的数字比较大
 *  0 : 恭喜！你猜对了！
 *
 *  思路1 使用二分查找法
 */
public class L374GuessTheSizeOfTheNumber {
    public static void main(String[] args) {
        System.out.println(1702766719 +1702766719);
    }
    public int guessNumber(int n) {
        double left = 1;
        double right = n;
        int index ;
        while (left <= right) {
            index = (int)((right + left) / 2);
            if (guess(index) == 1) {
                left = index + 1;
            } else if (guess(index) == -1) {
                right = index - 1;
            }else {
                return index;
            }
        }

        return -1;
    }

   int guess(int f) {
        return -1;
    }
}
