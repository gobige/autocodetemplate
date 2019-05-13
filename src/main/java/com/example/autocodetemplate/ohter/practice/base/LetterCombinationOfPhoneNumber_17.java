package com.example.autocodetemplate.ohter.practice.base;

import java.util.ArrayList;
import java.util.List;

/**
 *17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 思路：使用循环嵌套，实现，递归实现，特别要注意引用传递和参数传递的问题
 *
 * 时间复杂度约等于3的n次方
 * 空间复杂度
 */
public class LetterCombinationOfPhoneNumber_17 {


    public static void main(String[] args) {
        List<String> list = letterCombinations("2345");
        System.out.println(list + "" + list.size());
    }

    public static List<String> letterCombinations(String digits) {
        char[] letters = digits.toCharArray();
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>(0);
        }

        // 生成int数组
        Integer[] digitNums = new Integer[letters.length];

        for (int i = 0; i < letters.length; i++) {
            digitNums[i] = Integer.valueOf(letters[i]+"");
        }

        double m = new Double(3);
        double n = new Double(letters.length);
        List<String> list = new ArrayList<>(new Double(Math.pow(m, n)).intValue());
        // 确定第一层
        for (char a : EnumLetterOfPhoneNumber.findByNum(digitNums[0]).letters) {
            int level = 0;
            if (level == digitNums.length -1) {
                list.add(a + "");
            }else {
                recursive(EnumLetterOfPhoneNumber.findByNum(digitNums[level + 1]).letters, digitNums, a + "", level + 1, list);
            }
        }

        return list;
    }

    /**
     * @param letters       当前数字对于字母集合
     * @param digits        所有数字字符串集合
     * @param str 当前组合字符串
     * @param level         当前层数
     */
    public static void recursive(char[] letters, Integer[] digits, String str, Integer level, List<String> list) {
        for (char a : letters) {
            if (level == digits.length - 1) {
                list.add(str + a);
            } else {
                recursive(EnumLetterOfPhoneNumber.findByNum(digits[level + 1]).letters, digits, str + a, level + 1, list);
            }
        }
    }


    enum EnumLetterOfPhoneNumber {
        TWO(2, new char[]{'a', 'b', 'c'}),
        THREE(3, new char[]{'d', 'e', 'f'}),
        FOUR(4, new char[]{'g', 'h', 'i'}),
        FIVE(5, new char[]{'j', 'k', 'l'}),
        SIX(6, new char[]{'m', 'n', 'o'}),
        SEVEN(7, new char[]{'p', 'q', 'r', 's'}),
        EIGHT(8, new char[]{'t', 'u', 'v'}),
        NINE(9, new char[]{'w', 'x', 'y', 'z'});

        private Integer num;
        private char[] letters;

       static EnumLetterOfPhoneNumber findByNum(Integer num) {
            for (EnumLetterOfPhoneNumber letterOfPhoneNumber : EnumLetterOfPhoneNumber.values()) {
                if (letterOfPhoneNumber.getNum().equals(num)) {
                    return letterOfPhoneNumber;
                }
            }

            return null;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public char[] getLetters() {
            return letters;
        }

        public void setLetters(char[] letters) {
            this.letters = letters;
        }

        EnumLetterOfPhoneNumber(Integer num, char[] letters) {
            this.num = num;
            this.letters = letters;
        }
    }

}

