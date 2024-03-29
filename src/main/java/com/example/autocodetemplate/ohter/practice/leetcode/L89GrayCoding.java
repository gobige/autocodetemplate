package com.example.autocodetemplate.ohter.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 */
public class L89GrayCoding {
    public List<Integer> grayCode(int n) {
        Double maxNum = Math.pow(2, n);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < maxNum; i++) {
            list.add(i);
        }

        return list;
    }

    public static void main(String[] args) {
        String binary = Integer.toBinaryString(3);

        System.out.println(Integer.parseInt(binary,2));
    }
}
