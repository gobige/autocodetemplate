package com.example.autocodetemplate.ohter.practice.leetcode;

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

        System.out.println(reorganizeString("zhmyo"));
    }

    /**
     * 1. 将字符串字符提取到 priorityQueue中
     * 2. 每次获取大顶推两个字符，直到所有的字符被出堆
     * @param S
     * @return
     */
    public static String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        int[] count = new int[26];

        int max = 0;
        char[] chars = S.toCharArray();
        for (char aChar : chars) {
            count[aChar - 'a']++;
            if (count[aChar - 'a'] > max) {
                max = count[aChar - 'a'];
            }
        }

        if (1 < 2 * max - chars.length) {
            return "";
        }

        PriorityQueue<Character> priorityQueue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return count[(Character)o2 - 'a'] - count[(Character)o1 - 'a'];
            }
        });

        for (char i = 'a'; i <= 'z'; i++) {
            if (count[i - 'a'] > 0) {
                priorityQueue.offer(i);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            // 先出堆，再入堆，便于再次堆排序
            Character first = priorityQueue.poll();
            Character second = priorityQueue.poll();

            stringBuilder.append(first).append(second);

            int indexFirst = (Character) first - 'a';
            count[indexFirst]--;
            int indexSecond = (Character) second - 'a';
            count[indexSecond]--;

            if (count[indexFirst] > 0) {
                priorityQueue.offer(first);
            }
            if (count[indexSecond] > 0) {
                priorityQueue.offer(second);
            }

            if (priorityQueue.size() == 1) {
                stringBuilder.append(priorityQueue.poll());
                break;
            }
            if (priorityQueue.size() == 0) {
                break;
            }
        }

        return stringBuilder.toString();
    }


//    public String reorganizeString(String S) {
//        if (S == null || S.length() == 0) {
//            return "";
//        }
//
//        char[] chars = S.toCharArray();
//        Map<Character, Integer> treeMap = new TreeMap();
//
//        for (char aChar : chars) {
//            if (treeMap.containsKey(aChar)) {
//                treeMap.put(aChar, treeMap.get(aChar) + 1);
//            } else {
//                treeMap.put(aChar, 1);
//            }
//        }
//
//        Set<Map.Entry<Character, Integer>> entries = treeMap.entrySet();
//
//        int max = entries.stream().findFirst().get().getValue();
//        if (1 < chars.length - max) {
//            return "";
//        }
//
//        // 使用最大堆来进行 拼接字符串
//        StringBuilder stringBuilder = new StringBuilder();
//
//        while (true) {
//            entries = treeMap.entrySet();
//            Map.Entry entry1 = entries.stream().findFirst().get();
//            entry1
//        }
//    }


}
