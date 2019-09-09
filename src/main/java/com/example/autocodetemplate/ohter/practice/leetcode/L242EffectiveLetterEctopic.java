package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 思路1：遍历S 放入一个 26大小的数组N，对应放入+1， 然后遍历t，遍历 从数组N-1，最后看数组N是否都为0
 */
public class L242EffectiveLetterEctopic {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf('a'));
    }

    public boolean isAnagram(String s, String t) {
        int[] letterArr = new int[26];

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        for (char c : s1) {
            letterArr[((int) c - 97)]++;
        }

        for (char c : t1) {
            letterArr[((int) c - 97)]--;
        }


        for (int i : letterArr) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
