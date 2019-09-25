package com.example.autocodetemplate.ohter.practice.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 *
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 思路1 ：先求出所有正因子，再计算正因子之和
 * 时间复杂度 2N（做优化 判断个位是否被整除的数） 空间复杂度N
 */
public class L507PerfectNumber {
    public static void main(String[] args) {
        L507PerfectNumber l = new L507PerfectNumber();
        l.checkPerfectNumber(28);
    }


    public boolean checkPerfectNumber(int num) {
        if (num == 0) {
            return false;
        }
        List<Integer> list = new LinkedList<>();

        double sqrt = Math.sqrt(num);
        for (int i = 1; i < sqrt; i++) {
            if (num % i == 0) {
                list.add(i);
                if (i != 1) {
                    list.add(num / i);
                }
            }
        }

        Integer sum = 0;
        for (Integer i : list) {
            sum += i;
        }

        if (sum == num) {
            return true;
        } else {
            return false;
        }
    }


}
