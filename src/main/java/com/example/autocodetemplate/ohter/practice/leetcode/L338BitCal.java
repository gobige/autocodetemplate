package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 338. 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 */
    class L338BitCal {

    public static void main(String[] args) {

//        System.out.println(1>>0&1);
        int[] s = countBits(2);
        System.out.println(s);
    }

    public static int[] countBits(int num) {
        int[] result = new int[num + 1];{};

        for (int i = 0; i <= num; i++) {
            int sum = 0;
            for (int j = 1; j < 33; j++) {
                sum += i >> j & 1;
            }
            result[i] = sum;
        }

        return result;
    }
}
