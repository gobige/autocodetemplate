package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 *
 * 思路1 先对p所有字符做hash
 */
public class L438FindAllTheLetterEctopicsInTheString {
    public List<Integer> findAnagrams(String s, String p) {
        Set pset = new HashSet();
        for (char c : p.toCharArray()) {
            pset.add(c);
        }

        return null;
    }
}


