package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * 注意:
 * <p>
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * <p>
 * 思路1
 */
public class L405ConvertNumbersToHexadecimalNumbers {
    public static void main(String[] args) {
        L405ConvertNumbersToHexadecimalNumbers d = new L405ConvertNumbersToHexadecimalNumbers();
        System.out.println(d.toHex(-1));
    }
    public String toHex(int num) {
        if (num < 0) {
            num = Double.valueOf(Math.pow(2, 32)).intValue() + 1 + num;
        }

        return recurse(num);
    }

    String recurse(long num) {
        if (num < 16) {

            return to16Format(num);
        }else {
            long divisor = num / 16;
            long remainder = num % 16;

            return recurse(divisor) + to16Format(remainder);
        }
    }
    String to16Format(long num) {
        if (num < 10) {
            return num + "";
        }

        switch ((int)num) {
            case 10:
                return "a";
            case 11:
                return "b";
            case 12:
                return "c";
            case 13:
                return "d";
            case 14:
                return "e";
            case 15:
                return "f";
        }

        return "";
    }
}
