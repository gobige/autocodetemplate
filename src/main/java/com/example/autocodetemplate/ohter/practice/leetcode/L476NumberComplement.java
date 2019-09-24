package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 *
 * 注意:
 *
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 */
public class L476NumberComplement {
    public static void main(String[] args) {
        System.out.println(findComplement(1));

    }
    public static int findComplement(int num) {
        String  binaryString = Integer.toBinaryString(num);
        char[] chars = binaryString.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1' && flag) {
                stringBuilder.append("0");
            } else if (chars[i] == '0') {
                flag = true;
                stringBuilder.append("1");
            }
        }

        return Integer.parseInt(stringBuilder.toString().equals("") ? "0" : stringBuilder.toString(), 2);
    }







}
