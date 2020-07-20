package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 *  n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
 *
 * 每 3 个士兵可以组成一个作战单位，分组规则如下：
 *
 * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
 * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
 *
 * 思路1，先选定一个位，在选出剩余两个位
 */
public class L1395CountTheNumberOfCombatUnits {

    public static void main(String[] args) {
        System.out.println(numTeams(new int[]{2, 5, 3, 4, 1}));
    }

    public static int numTeams(int[] rating) {
        int count = 0;

        int size = rating.length;
        int indexLeft;
        int indexRight;
        for (int i = 0; i < size; i++) {
            indexLeft = i + 1;
            indexRight = size - 1;
            while (indexLeft < indexRight) {
                if (rating[i] < rating[indexLeft] && rating[indexLeft] < rating[indexRight]) {
                    count++;
                }else if (rating[i] > rating[indexLeft] && rating[indexLeft] > rating[indexRight]){
                    count++;
                }

                if (indexLeft < indexRight - 1) {
                    indexRight--;
                }else {
                    indexLeft++;
                    indexRight = size - 1;
                }
            }
        }

        return count;
    }
}