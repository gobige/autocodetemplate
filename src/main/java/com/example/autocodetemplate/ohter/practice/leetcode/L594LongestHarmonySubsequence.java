package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 594. 最长和谐子序列
 *  和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 *
 *  现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 *  思路1 使用hashmap计算，2排序 3 求最长序列
 */
public class L594LongestHarmonySubsequence {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>(12);

        findLHS(new int[]{0, 3, 1, 3, 3, 3, 0, 1, 0, 2, 0, 3, 1, 3, -3, 2, 0, 3, 1, 2, 2, -3, 2, 2, 3, 3});
    }

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });
        Object[] entries = list.toArray();

        int max = 0;
        Map.Entry<Integer, Integer> pre = (Map.Entry)entries[0];
        for (int i = 1; i < entries.length; i++) {
            Map.Entry<Integer, Integer> entry = (Map.Entry)entries[i];
            if (Math.abs(entry.getKey() - pre.getKey()) == 1) {
                max = entry.getValue() + pre.getValue() > max ? entry.getValue() + pre.getValue() : max;
            }
            pre = entry;
        }

        return max;
    }
}
