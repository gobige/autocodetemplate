package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 思路1 使用biset加入两个数组的值，并将重复的数字放入另一个数组，去重
 * 思路2 使用set集合存放两个数组值，然后遍历获取set集合值
 * 思路3 使用retailAll
 */
public class L349TheIntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] ss = new int[]{1, 2, 2, 1};
        int[] asd = new int[]{2, 2};

        System.out.println(intersection(ss, asd));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(nums1.length);
        Set<Integer> set2 = new HashSet<>(nums2.length);


        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }

        set1.retainAll(set2);

        int[] ss = new int[set1.size()];
        Iterator iterator = set1.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            int num = (int) iterator.next();
            ss[i] = num;
            i++;
        }

        return ss;
    }
    public static int[] intersection1(int[] nums1, int[] nums2) {
        BitSet bitSet = new BitSet();

        for (int i : nums1) {
            bitSet.set(i);
        }

        List<Integer> Intersections = new LinkedList();
        for (int i : nums2) {
            if (bitSet.get(i)) {
                Intersections.add(i);
            }
        }

        Intersections = Intersections.stream().distinct().collect(Collectors.toList());

        int[] ss = new int[Intersections.size()];
        for (int i = 0; i < Intersections.size(); i++) {
            ss[i] = Intersections.get(i);
        }

        return ss;
     }
}
