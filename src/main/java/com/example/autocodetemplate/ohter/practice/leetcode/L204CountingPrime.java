package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 思路1：厄拉多塞筛法
 * ：先将 2~n 的各个数放入表中，然后在2的上面画一个圆圈，然后划去2的其他倍数；
 * 第一个既未画圈又没有被划去的数是3，将它画圈，再划去3的其他倍数；
 * 现在既未画圈又没有被划去的第一个数 是5，将它画圈，并划去5的其他倍数……依次类推，一直到所有小于或等于 n 的各数都画了圈或划去为止。
 * 这时，表中画了圈的以及未划去的那些数正好就是小于 n 的素数。
 */
public class L204CountingPrime {

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    public static int countPrimes(int n) {
        int[] arr = new int[n];

        int i = 2;
        boolean end;
        while (i < arr.length) {
            end = true;
            int j = 2;
            while (j * i < arr.length) {
                arr[j * i] = 1;
                j++;
            }

            for (int a = i + 1; a < arr.length; a++) {
                if (arr[a] == 0) {
                    i = a;
                    end = false;
                    break;
                }
            }

            if (end) {
                break;
            }
        }

        int count = 0;
        for (int a = 2; a < arr.length; a++) {
            if (arr[a] == 0) {
                count++;
            }
        }
        return count;
    }
}
