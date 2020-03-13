package com.example.autocodetemplate.ohter.practice.base.jvmoptim;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */

/**
 * 反编译查看字符串的拼接,java8以后版本自动拼装
 */
public class CompileAndDecompileTest {
    public static void main(String[] args) {
        String testStr = "aa" + "bb" + "cc";
        System.out.println(testStr);

    }
}
