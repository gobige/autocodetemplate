package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * 给出一个区间的集合，合并所有重叠的区间。
 *
 */
public class MergingInterval56 {

    public static void main(String[] args) {
        int[][] ss = new int[][]{{1, 4}, {5, 6}};
        MergingInterval56 s = new MergingInterval56();

        System.out.println(s.merge(ss));
    }

    public  int[][] merge(int[][] intervals) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < intervals.length; i++) {
            // 扩大两倍 解决整数相连的问题{1, 4}, {5, 6}
            for (int j = intervals[i][0]*2; j <= intervals[i][1]*2; j++) {
                bitSet.set(j);
            }
        }

        int n = 1;
        int low = -1;
        int high = -1;
        int num = 0;
        List<int[]> objs = new ArrayList<>();
        while (n <= bitSet.size()) {
            if (bitSet.get(n)) {
                if (low == -1) {
                    low = n;
                }else {
                    high = n;
                }
            }else {
                if (low != -1 && high != -1) {
                    objs.add(new int[]{low/2, high/2});
                    low = -1;
                    high = -1;
                    num++;
                }
            }
            n++;
        }
        int[][] mergeArr = new int[num][2];

        for (int i = 0; i < objs.size(); i++) {
            mergeArr[i] = objs.get(i);
        }

        return mergeArr;
    }

    public static int[][] merge1(int[][] intervals) {
        int mergeCount = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0] && intervals[i][0] <= intervals[i + 1][1]) {
                intervals[i + 1][0] = Math.min(intervals[i][0], intervals[i+1][0]);
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
                intervals[i][0] = -1;
                intervals[i][1] = -1;
                mergeCount++;
            }
        }

        int[][] mergeArr = new int[intervals.length - mergeCount][2];
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] != -1 && intervals[i][0] != -1) {
                mergeArr[count][0] = intervals[i][0];
                mergeArr[count][1] = intervals[i][1];
                count++;
            }
        }

        return mergeArr;
    }
}
