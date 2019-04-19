package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: 从字符串中获取最长回文字符串</p>
 * <p>JDK version used JDK1.8</p>
 *
 * 解题思路 和 获取无重复字符最长子串一样的思路
 * @version 1.0
 */
public class LongestPalindromeFind {

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        // 字符 字符最近一次出现的下标位置
        Map<Character, Integer> characterMap = new HashMap<>(chars.length);
        Integer maxSingle = 0;
        Integer maxIndex = 0;
        Integer minIndex = 0;
        Integer index = 0;
        for (int i = 0; i < chars.length; ) {
            if (characterMap.containsKey(chars[i])) {
                if (maxSingle < characterMap.size()) {
                    maxSingle = characterMap.size();
                    maxIndex =
                }
                index = characterMap.get(chars[i]);
                i = index + 1;
                characterMap.clear();
            } else {
                characterMap.put(chars[i], i);
                i++;
            }
        }

        if (maxSingle < characterMap.size()) {
            maxSingle = characterMap.size();
        }

        return maxSingle;
    }


}
