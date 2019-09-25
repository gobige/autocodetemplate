package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 520. 检测大写字母
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 思路1：遍历：规则匹配，返回
 */
public class L520DetectCapitalLetters {
    public boolean detectCapitalUse(String word) {

        if (word.toUpperCase() == word) {
            return true;
        }

        if (word.toLowerCase() == word) {
            return true;
        }

        char[] chars = word.toCharArray();

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] > 64 && chars[i] < 91) {
                return false;
            }
        }

        return true;
    }
}
