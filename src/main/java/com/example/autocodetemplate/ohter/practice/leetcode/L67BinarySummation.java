package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 思路 先转化为int数组 再模拟二进制进位
 * 执行用时 :11 ms, 在所有 Java 提交中击败了16.03%的用户
 * 内存消耗 :36.4 MB, 在所有 Java 提交中击败了54.01%的用户
 */
public class L67BinarySummation {
    public static void main(String[] args) {
        System.out.println(addBinary("1011", "1010"));
    }

    public static String addBinary(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        int[] intA = new int[charsA.length];
        for (int i = 0; i < charsA.length; i++) {
            intA[i] = Integer.valueOf(String.valueOf(charsA[i]));
        }

        int[] intB = new int[charsB.length];
        for (int i = 0; i < charsB.length; i++) {
            intB[i] = Integer.valueOf(String.valueOf(charsB[i]));
        }


        int[] intResult = new int[intA.length > intB.length ? intA.length : intB.length];

        int ai = intA.length - 1;
        int bi = intB.length - 1;
        int ri = intResult.length - 1;
        boolean carry = false;
        while (true) {
            // 前一位进位
            if (carry) {
                // 两数相加是否进位
                if (intA[ai] + intB[bi] + 1 > 1) {
                    intResult[ri] = (intA[ai] + intB[bi] + 1) % 2;
                    carry = true;
                }else {
                    intResult[ri] = intA[ai] + intB[bi] + 1;
                    carry = false;
                }
            } else {
                if (intA[ai] + intB[bi] > 1) {
                    intResult[ri] = 0;
                    carry = true;
                } else {
                    intResult[ri] = intA[ai] + intB[bi];
                    carry = false;
                }
            }

            ai--;
            bi--;
            ri--;

            // 数组长的int数组 剩余填充
            if (ai < 0) {
                while (bi > -1) {
                    if (carry == true) {
                        if (intB[bi] + 1 > 1) {
                            intResult[ri] = 0;
                            carry = true;
                        }else {
                            intResult[ri] = 1;
                            carry = false;
                        }
                    } else {
                        intResult[ri] = intB[bi];
                        carry = false;
                    }
                    bi--;
                    ri--;
                }
            }

            if (bi < 0) {
                while (ai > -1) {
                    if (carry == true) {
                        if (intA[ai] + 1 > 1) {
                            intResult[ri] = 0;
                            carry = true;
                        }else {
                            intResult[ri] = 1;
                            carry = false;
                        }
                    }else {
                        intResult[ri] = intA[ai];
                        carry = false;
                    }
                    ai--;
                    ri--;
                }
            }

            // 是否最终进位
            if (bi < 0 && ai < 0) {
                if (carry) {
                    int[] newResult = new int[intResult.length + 1];
                    for (int i = 1; i < newResult.length; i++) {
                        newResult[i] = intResult[i-1];
                    }
                    intResult = newResult;
                    intResult[0] = 1;
                }

                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : intResult) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }
}
