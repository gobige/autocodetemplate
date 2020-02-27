package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 字符串转换整数 (atoi)
 * 思路  字符串转换为数组，取值，根据规则判断，得到整数  时间复杂度 o(4n)
 *
 */
public class L8Atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi("+1"));
    }

    public static int myAtoi(String str) {
        char[] converChars = str.toCharArray();

        StringBuilder cancelNullStrs = new StringBuilder(converChars.length);
        // 这里空间复杂度可换成单数组
        for (char c : converChars) {
            if (c != ' ') {
                cancelNullStrs.append(c);
            }
        }

        if (cancelNullStrs == null || cancelNullStrs.length() == 0) {
            return 0;
        }

        converChars = cancelNullStrs.toString().toCharArray();

        int index = 0;
        boolean symbol = false;

        StringBuilder stringBuilder = new StringBuilder(converChars.length);
        if ((57 < converChars[0] || converChars[0] < 48)) {
            if (converChars[index] == 45) {
                symbol = true;
            }else if (converChars[index] == 43) {

            }else {
                return 0;
            }
        }


        while (index < converChars.length) {
            if (converChars[index] != 0) {
                if (57 >= converChars[index] && converChars[index] >= 48) {
                    stringBuilder.append(converChars[index]);
                }
                if (46 ==  converChars[index]) {
                    break;
                }
            }

            index++;
        }

        if (stringBuilder.toString().length() == 0) {
            return 0;
        }
        int reverseInt = 0;
        try {
            reverseInt = Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            reverseInt = Integer.MAX_VALUE + 1;
        }

        if (symbol) {
            return -reverseInt;
        }
        return reverseInt;
    }

}
