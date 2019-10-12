package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 599. 两个列表的最小索引总和
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 *
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 *
 */
public class L599TheSmallestIndexSumOfTheTwoLists {
    public static void main(String[] args) {
        findRestaurant(new String[]{"Shogun","Tapioca Express","Burger King","KFC"}, new String[]{"KFC","Burger King","Tapioca Express","Shogun"});
    }
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap();

        List<String> list = new LinkedList<>();
        for (int i = 0; i < list2.length; i++) {
            map.put(list2[i], i);
        }

        int minIndex = list1.length + list2.length;
        for (int i = 0; i < list1.length; i++) {
            if (map.containsKey(list1[i])) {
                if (i + map.get(list1[i]) < minIndex) {
                    list.clear();
                    list.add(list1[i]);
                    minIndex = i + map.get(list1[i]);
                }else if (i + map.get(list1[i]) == minIndex) {
                    list.add(list1[i]);
                }
            }
        }

        return list.toArray(new String[0]);
    }
}
