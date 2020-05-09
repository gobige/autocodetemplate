package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 面试题 01.09. 字符串轮转  字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）
 *
 * 双指针，遍历匹配
 *
 */
public class L0109InterviewQuestions {
    public static void main(String[] args) {
     }



    public static boolean isFlipedString(String s1, String s2) {

        return s1.length() == s2.length() && (s1 + s1).indexOf(s2) > -1;
    }

}



