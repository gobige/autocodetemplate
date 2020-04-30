package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 */
public class L0102InterviewQuestions {
    public static void main(String[] args) {

        CheckPermutation("abc", "bac");
    }


    public static boolean CheckPermutation(String s1, String s2) {
        char[] a = s1.toCharArray();
        Arrays.sort(a);
        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);
        String s = String.valueOf(a);
        String s3 = String.valueOf(a2);
        if (s.equals(s3)) { return true; }
        return false;
    }
    public static boolean CheckPermutation2(String s1, String s2) {
        char[] a = s1.toCharArray();
        Arrays.sort(a);
        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);
        int length = a.length;
        int length2 = a2.length;
        for (int i = 0; i < length; i++) {
            for (int i1 = 0; i1 < length2; i1++) {
                if (a2[i1] == a[i]) {
                    a2[i1] = 0;break;
                }
            }
        }
        for (int i = 0; i < length2; i++) {
            if (a2[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
