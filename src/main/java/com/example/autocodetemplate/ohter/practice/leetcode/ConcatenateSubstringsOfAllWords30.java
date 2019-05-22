package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.List;

/**
 * 串联所有单词的子串
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 思路：RB算法每个word，每个word随意组合，每个组合进行匹配字符串s
 * 时间复杂度 n的平方乘以m
 */
public class ConcatenateSubstringsOfAllWords30 {
    public static void main(String[] args) {

    }

    public static List<Integer> findSubstring(String s, String[] words) {

    }

    /**
     * @param letters       当前数字对于字母集合
     * @param digits        所有数字字符串集合
     * @param str 当前组合字符串
     * @param level         当前层数
     */
    public static void recursive(char[] letters, Integer[] digits, String str, Integer level, List<String> list) {
        for (char a : letters) {
            if (level == digits.length - 1) {
                list.add(str + a);
            } else {
                recursive(LetterCombinationOfPhoneNumber_17.EnumLetterOfPhoneNumber.findByNum(digits[level + 1]).letters, digits, str + a, level + 1, list);
            }
        }
    }

    public static int strStr(String haystack, String needle) {

        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int needlelength = needle.length();
        int haystacklength = haystack.length();
        if (needlelength > haystacklength) {
            return -1;
        }

        int match = needle.hashCode();

        for (int i = 0; i <= haystacklength - needlelength; i++) {
            String patch = haystack.substring(i, needlelength + i);
            if (patch.hashCode() == match) {
                return i;
            }
        }

        return -1;
    }
}
