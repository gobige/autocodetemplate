package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;

/**
 * 290. 单词规律
 *
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 思路1. 映射匹配，需要构建一个映射表，使用hashMap来实现，key作为规则，value为规则对应值
 */
public class L290WordLaw {
    public static void main(String[] args) {

        String pattern = "abba";
        String s = "dog dog dog dog";

        System.out.println(wordPattern(pattern, s));
    }
    public static boolean wordPattern(String pattern, String s) {

        // 先对比匹配规则和待匹配字符串是否 数量是否匹配
        char[] chars = pattern.toCharArray();
        String[] strings = s.split(" ");

        if (chars.length != strings.length) {
            return false;
        }

        // 正向匹配
        HashMap<Character, String> cmap = new HashMap<>();
        // 反向匹配
        HashMap<String, Character> cmap2 = new HashMap<>();
        // 使用hashMap匹配规则
        for (int i = 0; i < chars.length; i++) {
            if (!cmap.containsKey(chars[i])) {
                cmap.put(chars[i], strings[i]);
            } else {
                String mapV = cmap.get(chars[i]);
                if (!mapV.equals(strings[i])) {
                    return false;
                }
            }

            if (!cmap2.containsKey(strings[i])) {
                cmap2.put(strings[i], chars[i]);
            } else {
                Character mapV = cmap2.get(strings[i]);
                if (!mapV.equals(chars[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
