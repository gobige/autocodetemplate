package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 383. 赎金信
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * 思路1 暴力破解  遍历ransom，每一个字符匹配magazine
 * 时间复杂度 n*m 空间复杂度 1
 *
 * 思路2 hash存储所有magazine的字符，遍历ransom进行对比hash对比
 * 时间复杂度 m +n 空间复杂度 m
 *
 */
public class L383RansomLetter {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] magazines = magazine.toCharArray();

        int[] leets = new int[26];
        for (char c : magazines) {
            int index = (int) c - 97;
            leets[index] = leets[index] + 1;
        }

        char[] ransoms = ransomNote.toCharArray();
        for (char c : ransoms) {
            int index = (int) c - 97;
            if (leets[index] == 0) {
                return false;
            }else {
                leets[index] = leets[index] - 1;
            }
        }

        return true;
    }
}
