package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 面试题 01.04. 回文排列  回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * 判断是否成双出现
 */
public class L0104InterviewQuestions {
    public static void main(String[] args) {
     }

    public static boolean canPermutePalindrome(String s) {
        char[] chars = s.toCharArray();
        Set set = new HashSet();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                set.remove(chars[i]);
            }else {
                set.add(chars[i]);
            }
        }

        return set.isEmpty();
    }
}
