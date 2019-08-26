package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 思路1：使用hashMap进行映射，遍历字符串，如果途中发现不匹配，则不是同构
 */
public class L205IsomorphicString {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "aa"));
    }

    public static boolean isIsomorphic(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();

        if (chars.length != chart.length) {
            return false;
        }

        Map map = new HashMap(chars.length);

        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                if (!map.get(chars[i]).equals(chart[i])) {
                    return false;
                }
            }else {
                map.put(chars[i], chart[i]);
            }
            if (map.containsKey(chart[i])) {
                if (!map.get(chart[i]).equals(chars[i])) {
                    return false;
                }
            }else {
                map.put(chart[i], chars[i]);
            }
        }

        return true;
    }
}
