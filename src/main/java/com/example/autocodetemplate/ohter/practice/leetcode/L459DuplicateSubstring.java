package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *
 * 459. 重复的子字符串
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 思路1 从第一个字符开始，一直到s长度一半，匹配是否有匹配的字符串
 */
public class L459DuplicateSubstring {
    public boolean repeatedSubstringPattern(String s) {
        char[] chars = s.toCharArray();

        int matchIndex = 0;
        int strL = 1;
        while (strL < chars.length / 2 + 1) {
            if (chars.length % strL == 0) {
                String matchStr = s.substring(0, strL - 1);
                char[] matchChar = matchStr.toCharArray();
                int i = 0;
                matchIndex = 0;
                while (matchIndex < chars.length) {
                    if (matchChar[i] != chars[matchIndex]) {
                        break;
                    } else {
                        if (i == matchChar.length) {
                            i = 0;
                        } else {
                            i++;
                        }
                        matchIndex++;
                    }
                }

                if (i == matchChar.length && matchIndex == chars.length) {
                    return true;
                }
            }
            strL++;
        }

        return true;
    }
}
