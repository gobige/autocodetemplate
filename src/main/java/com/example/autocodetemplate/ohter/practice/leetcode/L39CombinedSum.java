package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 思路1：通过回溯算法和减枝来实现
 * 1. 想象为一棵树，每次减去数组中一个值，然后得到的差继续下一步，直到得到0即为一个组合
 * 2. 为了防止重复组合的产生，每次查询candidates的数作为begin要大于等于上次减去的数
 */
public class L39CombinedSum {


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        // 先对 candidates排序
        Arrays.sort(candidates);

        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        return lists;
    }

    public boolean ge(int target, int candindex, int[] candidates, List<Integer> list) {
        if (candindex >= candidates.length) {
            return false;
        }

        target = target - candidates[candindex];
        if (target == 0) {
            list.add(candidates[candindex]);
            return true;
        }
        if (target < 0) {
            return false;
        }


        boolean cutSuc = ge(target, candindex, candidates, list);
        if (cutSuc) {
            list.add(candidates[candindex]);
            return ge(target, candindex, candidates, list);
        } else {
            return ge(target, candindex++, candidates, list);
        }
    }
}
