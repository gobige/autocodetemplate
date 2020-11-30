package com.example.autocodetemplate.ohter.practice.leetcode;

import javax.validation.constraints.Max;
import java.util.*;

/**
 * 767. 重构字符串
 *
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串
 * 1. 思路1 贪心算法
 * 2. 思路2 根据字符分桶 计算字符数量最多的桶的个数是否超过所有桶的字符数 大于2 则不可以重构
 */
public class L767ReorganizeString {
    public static void main(String[] args) {

    }

    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        char[] chars = S.toCharArray();
        Map<Character, Integer> treeMap = new TreeMap();

        for (char aChar : chars) {
            if (treeMap.containsKey(aChar)) {
                treeMap.put(aChar, treeMap.get(aChar) + 1);
            } else {
                treeMap.put(aChar, 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entries = treeMap.entrySet();

        int max = entries.stream().findFirst().get().getValue();
        if (1 < chars.length - max) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entries) {
//            stringBuilder.append() TODO
        }

    }
}
