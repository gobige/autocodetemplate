package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 面试题 01.05. 一次编辑 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * 判断是否成双出现
 */
public class L0105InterviewQuestions {
    public static void main(String[] args) {
        oneEditAway("ab",
                "bc");
     }


    public static boolean oneEditAway(String first, String second) {
        char[] charsFirst = first.toCharArray();
        char[] charsSecond = second.toCharArray();

        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < charsFirst.length; i++) {
            char key = charsFirst[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        int extChar = 0;
        for (int i = 0; i < charsSecond.length; i++) {
            char key = charsSecond[i];
            if (map.containsKey(key)) {
                if (map.get(key) > 1) {
                    map.put(key, map.get(key) - 1);
                }else {
                    map.remove(key);
                }
            }else {
                extChar++;
            }
        }

        if (map.size() == 0 && extChar < 2) {
            return true;
        }
        if (map.size() == 1) {
           Set<Map.Entry> keySets = (Set) map.keySet();
            Object c = keySets.iterator().next();

            if (map.get(c) < 2 && extChar == 0) {
                return true;
            }

            return false;
        }

        return false;
    }
}
