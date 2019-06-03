package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 思路：根据规则读出上个字符串的数字，相同数字为量词，数字值为名词  量词+名词的结构的到下一个字符串组成
 *
 * 时间复杂度 n
 */
public class ReportNumber38 {

    public static void main(String[] args) {

        System.out.println(countAndSay(4));
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        int i = 1;
        String curStr = "1";
        while (i < n) {

            String returnStr = "";
            int quantifier = 0;

            char[] chars = curStr.toCharArray();
            char curChar = chars[0];

            String cache = "";
            for (char c : chars) {
                if (c == curChar) {
                    quantifier++;
                } else {
                    returnStr = returnStr + cache;
                    quantifier = 1;
                    curChar = c;
                }
                cache = quantifier + "" + c;
            }

            if (cache != "") {
                returnStr = returnStr + cache;
            }

            curStr = returnStr;
            i++;
        }

        return curStr;
    }
}
