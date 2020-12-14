package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 思路1：循环遍历获取每个字符串，对每个字符串进行排序，然后hash分桶
 *
 */
public class L49LetterDysphoricGrouping {

    public static void main(String[] args) {

        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strings);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> lists = new ArrayList<>();
        HashMap<String, Integer> set = new HashMap();
        int buket = 0;
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);
            if (set.containsKey(key)) {
                int index = set.get(key);
                List<String> list = lists.get(index);
                list.add(str);
            } else {
                set.put(key, buket);
                buket++;
                List<String> list = new ArrayList<>();
                list.add(str);
                lists.add(list);
            }
        }

        return lists;
    }

}
