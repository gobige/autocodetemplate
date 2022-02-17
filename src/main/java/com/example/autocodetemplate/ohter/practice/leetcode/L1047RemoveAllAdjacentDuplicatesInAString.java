package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 *  删除字符串中的所有相邻重复项
 *
 *  给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 思路1 双向指针 递归删除
 */
public class L1047RemoveAllAdjacentDuplicatesInAString {

    public static void main(String[] args) {


    }

    public String removeDuplicates(String S) {
        char[] chars = S.toCharArray();

        char prec = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == prec) {

            }
        }

        return "";
    }
}
