package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String str = "dvdf";
        int len = lengthOfLongestSubstring(str);

        System.out.println(len);

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] charCorrespondNums = getCharCorrespondNums(chars);
        int count =  getCharCorrespondNums(charCorrespondNums);

        return count;
    }

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
