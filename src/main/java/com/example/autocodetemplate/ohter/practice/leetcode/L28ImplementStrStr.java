package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 *
 * 思路1 使用RK算法
 * 思路2 使用KMP算法
 */
public class L28ImplementStrStr {

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";

        System.out.println(strStr(haystack, needle));
    }

    /**
     *
     * @param haystack
     * @param needle
     * @return
     */
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
