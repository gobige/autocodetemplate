package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 思路 将字符串数组 前缀一个一个对比，即时返回
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (1 == strs.length ) {
            return strs[0];
        }

        // 拆分成列表 对象 存储数组结构
        List<CharsAraay> characters = new ArrayList<CharsAraay>(strs.length);
        for (String string : strs) {
            char[] chars = string.toCharArray();
            if (chars.length == 0) {
                return "";
            }

            CharsAraay charsAraay = new CharsAraay(chars);
            characters.add(charsAraay);
        }

        // core 比较
        StringBuilder result = new StringBuilder("");
        int index = 0;
        while (true) {

            // 第一个字符串作为基准
            char[] firstchars = characters.get(0).getChars();
            if (firstchars.length == index) {
                return result.toString();
            }
            char prefix = firstchars[index];
            for (int i = 1; i < characters.size(); i++) {
                CharsAraay charsAraay = characters.get(i);
                char[] s = charsAraay.getChars();

                if (s.length == index) {
                    return result.toString();
                }
                if (prefix != s[index]) {
                    return result.toString();
                }
            }

            result.append(prefix);
            index++;
        }
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abca","abc"}));
    }
}

class CharsAraay {
    char[] chars;

    CharsAraay(char[] chars) {
        this.chars = chars;
    }
    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }
}