package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * 思路1：根据十进制算法特点和 该题特点，比如 11 和38得到的答案一样，1和10答案一样，除以9得到余数就是1~9之间
 *
 *
 */
public class L258AddYouAll {

    public int addDigits(int num) {

        return (num-1) % 9 +1;
    }
}
