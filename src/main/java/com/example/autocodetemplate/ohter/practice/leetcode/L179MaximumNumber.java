package com.example.autocodetemplate.ohter.practice.leetcode;

import com.example.autocodetemplate.ohter.practice.arithmetic.SortArithmetic;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 思路1：通过观察，想重新排列成为一个最大的整数，只需要获得高位数数字（高位数相等则进行下一位数排序） 并进行排序，即可得到合成最大数字
 * 有点基数排序的思想，但是我们是向右补零 （121,12），shib
 *
 * 思路2：使用字符串对比，排序，  撸半天 才发现这么简单 无奈
 */
public class L179MaximumNumber {

    public static void main(String[] args) {
       new L179MaximumNumber(). largestNumber(new int[]{0, 0});
    }

    public   String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = nums[i] + "";
        }

        Arrays.sort(numStr, new LargerNumberComparator());

        if (numStr[0].equals("0")) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : numStr) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }

    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }
}
