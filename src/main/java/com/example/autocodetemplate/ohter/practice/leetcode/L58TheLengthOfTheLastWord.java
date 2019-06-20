package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 */
public class L58TheLengthOfTheLastWord {

    public static void main(String[] args) {

        System.out.println(lengthOfLastWord("a "));  ;
    }
    public static int lengthOfLastWord(String s) {

        char[] chars = s.trim().toCharArray();

        int index = -1;
        for (int i = chars.length-1; i >= 0; i--) {
            if (chars[i] == 32) {
                index = i+1;
                break;
            }
        }

        if (index != -1) {
            return chars.length - index;
        }else {
            return chars.length;
        }
    }
}
