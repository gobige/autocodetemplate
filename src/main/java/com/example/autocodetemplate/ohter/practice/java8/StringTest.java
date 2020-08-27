package com.example.autocodetemplate.ohter.practice.java8;

import java.util.StringJoiner;


/**
 * string test
 */
public class StringTest {
    public static void main(String[] str) {
        // StringJoiner类的使用
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("str1");sj.add("str2");
        StringJoiner sj2 = new StringJoiner(",", "{", "}");
        sj2.add("str3");sj2.add("str4");
        // 将sj2的元素合并到sj1中
        sj.merge(sj2);
        System.out.println(sj.toString() + sj.length());
    }
}
