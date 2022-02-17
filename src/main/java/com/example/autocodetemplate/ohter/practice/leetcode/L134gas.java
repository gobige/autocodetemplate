package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.Arrays;

/**
 * 134. 加油站
 *
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 */
public class L134gas {
    public static void main(String[] args) {
        canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
    }
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length < 1 || cost == null || cost.length <1) {
            return -1;
        }

        int gasSum = 0;
        int costSum = 0;

        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
        }

        if (gasSum < costSum) {
            return -1;
        }

        int balance = 0;
        int startInd = 0;
        for (int i = 0; i < gas.length; i++) {
            balance += gas[i] - cost[i];

            if (balance < 0) {
                startInd = i + 1;
                balance = 0;
            }
        }

        return startInd;
    }
}
