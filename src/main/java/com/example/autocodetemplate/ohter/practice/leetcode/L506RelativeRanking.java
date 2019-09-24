package com.example.autocodetemplate.ohter.practice.leetcode;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 506. 相对名次
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 *
 * (注：分数越高的选手，排名越靠前。)
 */
public class L506RelativeRanking {
    public static void main(String[] args) {
        System.out.println(findRelativeRanks(new int[]{10, 3, 8, 9, 4}));
    }

    public static String[] findRelativeRanks(int[] nums) {
        String[] strings = new String[nums.length];

        // 得分，次序map
        Map<Integer, Integer> sortMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            sortMap.put(nums[i], i);
        }

        List<Map.Entry<Integer, Integer>> li = new ArrayList<Map.Entry<Integer, Integer>>(sortMap.entrySet());

        li =  li.stream().sorted(Comparator.comparing(Map.Entry::getKey,Comparator.reverseOrder())).collect(Collectors.toList());

        Iterator iterator = li.iterator();
        int j = 1;
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            if (j == 1) {
                strings[(Integer) entry.getValue()] = "Gold Medal";
            } else if (j == 2) {
                strings[(Integer) entry.getValue()] = "Silver Medal";
            } else if (j == 3) {
                strings[(Integer) entry.getValue()] = "Bronze Medal";
            } else {
                strings[(Integer) entry.getValue()] = j + "";
            }
            j++;
        }

        return strings;
    }

}
