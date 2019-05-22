package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: 从字符串中获取最长回文字符串</p>
 * <p>JDK version used JDK1.8</p>
 *
 * 解题思路 和 获取无重复字符最长子串一样的思路
 * @version 1.0
 */
public class LongestPalindromeFind5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ac"));

    }

    // 走最长步长开始，相继减少
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        if (s.length() == 2 && chars[0] != chars[1]) {
            return s.substring(0,1);
        }

        int beginIndex = 0;
        int stepSize = s.length() - 1;


        while (true) {
            if (stepSize == 0) {
                return "";
            }

            if (beginIndex + stepSize < s.length()) {
                if (chars[beginIndex] == chars[beginIndex +stepSize]) {
                    return s.substring(beginIndex, beginIndex +stepSize + 1);
                }

                beginIndex++;
            }else {
                stepSize--;
                beginIndex = 0;
            }
        }

    }

}
