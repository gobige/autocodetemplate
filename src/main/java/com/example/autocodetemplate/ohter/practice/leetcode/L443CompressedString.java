package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 443. 压缩字符串
 * 给定一组字符，使用原地算法将其压缩。
 *
 * 压缩后的长度必须始终小于或等于原数组长度。
 *
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 *
 * 在完成原地修改输入数组后，返回数组的新长度。
 *
 */
public class L443CompressedString {
    public static void main(String[] args) {
        System.out.println( compress(new char[]{'a'}));
    }
    public static int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }

        int num = 1;
        int encryCount = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] != chars[i + 1]) {
                encryCount = 1;
                num++;
            } else if (chars[i] == chars[i + 1] && encryCount == 1) {
                encryCount = 2;
                num++;
            }
        }


        return num;
    }
}
