package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 思路1，一对一匹配，右移
 */
public class L392JudgeSubSequence {
    public static void main(String[] args) {


        String subStr = "abc";
        String parStr = "ahbgdc";

        System.out.println(isSubsequence(subStr, parStr));
    }

    public static boolean isSubsequence(String s, String t) {
        int subLenth = s.length();
        if (s == null || subLenth == 0 ) {
            return true;
        }
        if (  t == null || t.length() == 0 ) {
            return false;
        }

        char[] chars = t.toCharArray();

        int subIndex = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (s.charAt(subIndex) == chars[i]) {
                subIndex++;
            }

            if (subIndex > subLenth - 1) {
                break;
            }
        }

        if (subIndex == subLenth) {
            return true;
        }

        return false;
    }
}
