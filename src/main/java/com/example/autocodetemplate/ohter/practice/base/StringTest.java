package com.example.autocodetemplate.ohter.practice.base;

import org.apache.commons.lang.StringUtils;

/**
 * 有关字符串的练习
 */
public class StringTest {
    public static void main(String[] args) {
        String blankStr = null;
        // 判断字符串是否为null或""或"    "
        System.out.println("StringUtils.isBlank  " + StringUtils.isBlank(blankStr));

        // 判断字符串是否为null或""
        System.out.println("StringUtils.isEmpty  " + StringUtils.isEmpty(blankStr));

    }


    public static void java11() {
        String blankStr = "  ";

        // 判断字符串是否为"    "
//        System.out.println("String.isBlank   " + blankStr.isBlank());

        // 去除首尾空格
//        System.out.println("String.strip   " + blankStr.strip());

        // 去除尾部空格
//        System.out.println("String.strip   " + blankStr.stripTrailing());

        // 去除首部空格
//        System.out.println("String.strip   " + blankStr.stripLeading());

        // 复制字符串 复制次数有限制
//        System.out.println("String.repeat   " + blankStr.repeat(3));

        // 行数统计
//        System.out.println("String.repeat   " + blankStr.lines().count());





    }

}
