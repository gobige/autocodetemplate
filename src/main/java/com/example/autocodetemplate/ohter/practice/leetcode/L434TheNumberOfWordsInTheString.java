package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 思路1：
 */
public class L434TheNumberOfWordsInTheString {
    public static void main(String[] args) {
        System.out.println(countSegments("Hello, my name is John"));

    }

    public static int countSegments(String s) {
        if (s.replaceAll(" +", "").length() == 0) {
            return 0;
        }

        s = s.replaceAll(" +"," ").trim();

        char[] chars = s.toCharArray();
        int sum = 1;
        for (char c : chars) {
            if (c == ' ') {
                sum++;
            }
        }

        return sum;
    }
}
