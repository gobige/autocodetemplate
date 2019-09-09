package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * a  e  i  o  u;
 * 思路1 双指针 遇到元音字母交换位置
 */

public class L345ReverseVowelsInAString {
    public static void main(String[] args) {
        reverseVowels("ai");
    }
    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            while (chars[i] != 'a' && chars[i] != 'e' && chars[i] != 'i' && chars[i] != 'o' && chars[i] != 'u' && chars[i] != 'A' && chars[i] != 'E' && chars[i] != 'I' && chars[i] != 'O' && chars[i] != 'U' && i < j) {
                i++;
            }


            while (chars[j] != 'a' && chars[j] != 'e' && chars[j] != 'i' && chars[j] != 'o' && chars[j] != 'u' && chars[j] != 'A' && chars[j] != 'E' && chars[j] != 'I' && chars[j] != 'O' && chars[j] != 'U' && i < j) {
                j--;
            }

            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            i++;
            j--;
        }

        return String.valueOf(chars);
    }
}
