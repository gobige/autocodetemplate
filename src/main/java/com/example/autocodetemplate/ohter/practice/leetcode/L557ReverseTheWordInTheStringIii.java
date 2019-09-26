package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。\
 *
 * 思路1：遍历 反转字符，双指针
 * 时间复杂度 N 空间复杂度1
 */
public class L557ReverseTheWordInTheStringIii {
    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }


    public static String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : strs) {
            char[] chars = str.toCharArray();

            int left = 0;
            int right = chars.length;
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right-1];
                chars[right-1] = temp;
                left++;
                right--;
            }

            stringBuilder.append(String.valueOf(chars) + " ");
        }

        return stringBuilder.substring(0,s.length());
    }

}
