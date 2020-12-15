package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 单调递增的数字
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 思路1：根据题意，要想获得单调递增的最大整数，
 * 从个位开始对整数进行递减查询修改
 * 如果高位减1后需对之后低位进行修改为9
 */
public class L738MonotonicallyIncreasingNumbers {
    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(10));
    }
    public static int monotoneIncreasingDigits(int N) {

        // 改写为字符串
        String stringNum = String.valueOf(N);

        // 改写为字符
        char[] chars = stringNum.toCharArray();
        int length = chars.length;
        for (int i = length - 1; i > 0; i--) {
            Integer hpValue = Integer.valueOf(chars[i - 1]);
            Integer lpValue = Integer.valueOf(chars[i]);

            // 对比高位是否比低位小，若不小，对所有低位改写为9
            if (hpValue > lpValue) {
                chars[i - 1] = (char) (hpValue - 1);

                int j = i;
                while (j < length) {
                    //  优化，轮询碰到9结束改写
                    if (chars[j] == 57) {
                        break;
                    }
                    chars[j] = (char) (57);
                    j++;
                }
            }
        }

        return Integer.valueOf(String.valueOf(chars));
    }
}
