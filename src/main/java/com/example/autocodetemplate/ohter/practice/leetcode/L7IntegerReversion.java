package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 转换为数组，根据 规则思路反转  输出反转后整数  时间复杂度 O(2n)。
 */
public class L7IntegerReversion {

    public static void main(String[] args) {
        int num = -123;
        System.out.println(reverse(num));
    }

    public static int reverse(int x) {
        char[] chars = (x + "").toCharArray();

        StringBuilder stringBuilder = new StringBuilder(chars.length);
        int index = chars.length - 1;
        boolean symbol = false;
        while (true) {
            if (index < 0) {
                break;
            }

            if (57 >= chars[index] && chars[index] >= 48) {
                stringBuilder.append(chars[index]);
            }else {
                symbol = true;
            }
            index--;
        }

        int reverseInt = 0;
        try {
            reverseInt = Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            reverseInt = 0;
        }

        if (symbol) {
            return -reverseInt;
        }
        return reverseInt;
    }


}
