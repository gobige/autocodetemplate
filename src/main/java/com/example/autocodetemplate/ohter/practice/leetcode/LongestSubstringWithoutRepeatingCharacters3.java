package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: 获取无重复字符的最长子串</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
    public static void main(String[] args) {
        String str = "dvdf";

        int maxSingle = lengthOfLongestSubstring(str);

        System.out.println(maxSingle);
    }

    /**
     *  获取无重复字符的最长子串
     *      * 时间复杂度 最坏O(1/2 * n平方) 最好O(N)
     *      优化：针对重复字符点进行多位滑动
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        // 字符 字符最近一次出现的下标位置
        Map<Character, Integer> characterMap = new HashMap<>(chars.length);
        Integer maxSingle = 0;
        Integer index = 0;
        for (int i = 0; i < chars.length; ) {
            if (characterMap.containsKey(chars[i])) {
                if (maxSingle < characterMap.size()) {
                    maxSingle = characterMap.size();
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

    /**
     *  获取无重复字符的最长子串
     *      * 时间复杂度 最坏O(1/2 * n平方) 最好O(N)
     * @param s
     * @return
     */
//    public static int lengthOfLongestSubstring(String s) {
//        char[] chars = s.toCharArray();
//
//        Map<Character, Character> characterMap = new HashMap<>(chars.length);
//        Integer maxSingle = 0;
//        Integer index = 0;
//        for (int i = 0; i < chars.length; ) {
//            if (characterMap.containsKey(chars[i])) {
//                if (maxSingle < characterMap.size()) {
//                    maxSingle = characterMap.size();
//                }
//                index++;
//                i = index;
//                characterMap.clear();
//            } else {
//                characterMap.put(chars[i], chars[i]);
//                i++;
//            }
//        }
//
//        if (maxSingle < characterMap.size()) {
//            maxSingle = characterMap.size();
//        }
//
//        return maxSingle;
//    }

    /**
     * 失败算法
     * @param s
     * @return
     */
//    public static int lengthOfLongestSubstring(String s) {
//        if (s == null) {
//            return 0;
//        }
//        char[] chars = s.toCharArray();
//        int[] charCorrespondNums = getCharCorrespondNums(chars);
//        int count =  getCharCorrespondNums(charCorrespondNums);
//
//        return count;
//    }

    /**
     * 排列所有字符第几次出现，为1的肯定是不重复的，但是会出现好前缀遗漏的现象 失败算法
     * @param charCorrespondNums
     * @return
     */
    public static int getCharCorrespondNums(int[] charCorrespondNums) {
        int count = 0;
        if (charCorrespondNums != null && charCorrespondNums.length > 0) {
            int sum = 0;
            for (int i : charCorrespondNums) {
                if (i == 1) {
                    sum++;
                }else {
                    sum = 0;
                }

                if (count < sum) {
                    count = sum;
                }
            }
        }

        return count;
    }

    /**
     * 获取每个字符在字符串中出现的次数
     * @param chars
     * @return
     */
    public static int[] getCharCorrespondNums(char[] chars) {
        int[] charCorrespondNums = new int[chars.length];

        Map<Character, Integer> characterMap = new HashMap<>();
        Integer val = 0;
        for (int i = 0; i < chars.length; i++) {
            if (characterMap.containsKey(chars[i])) {
                val = characterMap.get(chars[i]) + 1;
            } else {
                val = 1;
            }
            characterMap.put(chars[i], val);
            charCorrespondNums[i] = val;
        }

        return charCorrespondNums;
    }
}
