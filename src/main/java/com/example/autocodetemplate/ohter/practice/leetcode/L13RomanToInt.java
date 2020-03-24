package com.example.autocodetemplate.ohter.practice.leetcode;

/**
 * 罗马转换为int类型
 *  罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
 *  类似于解释器模式，解释，累加
 */
public class L13RomanToInt {
    public static int romanToInt(String s) throws Exception{
        int result = 0;
        if (s == null || s.length() < 1) {
            return 0;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            int currentIndexValue = 0;
            EnumRomannum roman = EnumRomannum.findKey(chars[i]);
            currentIndexValue = roman.getValue();
            if (roman == null) {
                throw new Exception("非法罗马字符");
            }

            int nextIndex = i+1;
            if (nextIndex < chars.length) {
                EnumRomannum nextRoman = EnumRomannum.findKey(chars[nextIndex]);
                if (nextRoman.getValue() > roman.getValue()) {
                    currentIndexValue = -currentIndexValue;
                }
            }

            result = result + currentIndexValue;
        }


        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(L13RomanToInt.romanToInt("MCMXCIV"));
    }

}

enum EnumRomannum {
    I('I', 1, "罗马数字1"),
    V('V', 5, "罗马数字5"),
    X('X', 10, "罗马数字10"),
    L('L', 50, "罗马数字50"),
    C('C', 100, "罗马数字100"),
    D('D', 500, "罗马数字500"),
    M('M', 1000, "罗马数字1000");

    private Character key;
    private Integer value;
    private String desc;

    static EnumRomannum findKey(char key) {
        for (EnumRomannum enumRomannum : EnumRomannum.values()) {
            if (enumRomannum.key.equals(key)) {
                return enumRomannum;
            }
        }

        return null;
    }


    EnumRomannum(char key, Integer value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
