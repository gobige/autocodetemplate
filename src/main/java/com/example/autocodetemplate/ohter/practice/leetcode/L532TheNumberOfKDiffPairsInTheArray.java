package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SortArithmetic;
import com.google.common.primitives.Ints;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 532. 数组中的K-diff数对
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j),
 * 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 * 思路1: 双指针遍历
 * 时间复杂度2N  空间复杂度 1
 *
 * 思路2：使用两个hash结构a,b，遍历，一个存储第一次出现数字，第二个存储再次出现数字。
 * 然后遍历a看是否在a和b中有减去k后对应的值
 * 时间复杂度2N  空间复杂度 1
 */
public class L532TheNumberOfKDiffPairsInTheArray {
    public static void main(String[] args) {
        L532TheNumberOfKDiffPairsInTheArray l = new L532TheNumberOfKDiffPairsInTheArray();
        System.out.println(l.findPairs(new int[]{3, 1, 4, 1, 5}, 2));

    }
    public int findPairs(int[] nums, int k) {
        int left = 0;
        int right = 1;

        List<Integer> list = new LinkedList();
        for (int num : nums) {
            list.add(num);
        }
        list = list.stream().sorted().collect(Collectors.toList());

        Map<Integer, Integer> map = new HashMap<>();
        while (left < list.size() && right < list.size()) {
            int step = list.get(right) - list.get(left);
            if (step == k) {
                map.put(list.get(left), list.get(right));
                left++;
                right++;
            } else if (step < k) {
                right++;
            }else {
                left++;
                if (left == right) {
                    right++;
                }
            }
        }

        return map.size();
    }
}
