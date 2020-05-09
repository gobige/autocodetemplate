package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 面试题 01.06. 字符串压缩 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 */
public class L0106InterviewQuestions {
    public static void main(String[] args) {
        compressString("aabcccccaaa" );
     }

    public static String compressString(String S) {
        if (S.length() < 3) {
            return S;
        }
        char[] chars = S.toCharArray();

        char cur = chars[0];
        StringBuilder zipStr = new StringBuilder();
        int zipDept = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == cur) {
                zipDept++;
            }else {
                zipStr.append(cur).append(zipDept);
                zipDept = 1;
                cur = chars[i];
            }
        }
        zipStr.append(cur).append(zipDept);

        if (zipStr.length() < chars.length) {
            return zipStr.toString();
        }

        return S;
    }
}
