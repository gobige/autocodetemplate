package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;

/**
 * 387. 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 思路1 建一个容量为26的数组为次序数组，再建一个容量为26数组为字符数组，按字符出现的顺序排列，字符数组重复出现+1，遍历次序数组，第一个为1 的字符
 * 时间复杂度  n 空间复杂度2n
 */
public class L387TheFirstUniqueCharacterInTheString {
    public static void main(String[] args) {
        firstUniqChar("dddccdbba");
    }
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();

        List orders = new LinkedList<>();
        Map<Character, Integer> nums = new HashMap<>();
        int i = 0;
        for (char c : chars) {
            if (nums.containsKey(c)) {
                nums.put(c, nums.get(c) + 1);
            }else {
                nums.put(c, 1);
            }
            orders.add(c);
        }

        Iterator iterator = orders.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            char c = (char) iterator.next();
            if (nums.containsKey(c) && nums.get(c) == 1) {
                return index;
            }
            index++;
        }

        return -1;
    }
}