package com.example.autocodetemplate.ohter.practice.leetcode;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

/**
 * 605. 种花问题
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？
 * 能则返回True，不能则返回False。
 *
 * 思路1：遍历，连续3个空格可以放下一朵花，1+(n*2)=空格数
 */
public class L605FlowerProblems {
    public static void main(String[] args) {
        canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        List<Integer> newList = new LinkedList<>();
        newList.add(0);
        for (int i : flowerbed) {
            newList.add(i);
        }
        newList.add(0);

        int flowerNum = 0;
        int spaceNum = 0;
        for (int i : newList) {
            if (i == 1) {
                flowerNum += (spaceNum - 1) / 2;
                spaceNum = 0;
            }else {
                spaceNum++;
            }
        }

        flowerNum += (spaceNum - 1) / 2;

        if (n > flowerNum) {
            return false;
        }

        return true;
    }
}
