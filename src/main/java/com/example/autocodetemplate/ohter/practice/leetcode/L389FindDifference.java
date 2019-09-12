package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 389. 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 * 思路1 暴力 遍历s和t字符串，找出不同字符
 * 时间复杂度 n * m 空间复杂度 1
 *
 * 思路2 对s字符串做hash 遍历t得到不同字符
 *
 * 思路3 因为每一个字母都有ascall码，两个字符串相减得到的值就是该字符串ascall值
 *
 */
public class L389FindDifference {
    public char findTheDifference(String s, String t) {
        char[] chars = s.toCharArray();

        char[] tchars = t.toCharArray();
        int tSum = 0;
        int sSum = 0;
        for (char c : tchars) {
            tSum += (int) c;
        }
        for (char c : chars) {
            sSum += (int) c;
        }

        return  (char)(tSum - sSum);
    }

    public char findTheDifference1(String s, String t) {
        char[] chars = s.toCharArray();

        Map<Character,Integer> map = new HashMap<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }else {
                map.put(c,1);
            }
        }

        char[] tchars = t.toCharArray();
        for (char c : tchars) {
            if (!map.containsKey(c)) {
                return c;
            }
            if (map.get(c) == 0) {
                return c;
            }else {
                map.put(c, map.get(c) - 1);
            }
        }

        return 'a';
    }
}
