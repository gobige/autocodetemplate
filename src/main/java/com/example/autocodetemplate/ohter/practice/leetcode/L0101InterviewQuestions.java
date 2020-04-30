package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.BitSet;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 *
 */
public class L0101InterviewQuestions {

    public static void main(String[] args) {
        isUnique2("abc");

    }

    // 1ms
    public boolean isUnique(String astr) {
        BitSet bitSet = new BitSet();

        char[] chars = astr.toCharArray();

        int length = chars.length;
        char aChar;
        for (int i = 0; i < length; i++) {
            aChar = chars[i];
            if (bitSet.get(aChar)) {
                return false;
            } else {
                bitSet.set(aChar);
            }
        }

        return true;
    }
    // 0ms  避免了 额外的空间，双指针遍历
    public static boolean isUnique2(String astr) {
        BitSet bitSet = new BitSet();

        char[] chars = astr.toCharArray();
        int length = chars.length;
        char aChar;
        for (int i = 0; i < length; i++) {
            aChar = chars[i];
            if (astr.indexOf(aChar) != astr.lastIndexOf(aChar)) {
                return false;
            } else {
                bitSet.set(aChar);
            }
        }

        return true;
    }
}
