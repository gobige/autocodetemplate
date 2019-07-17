package com.example.autocodetemplate.ohter.practice.base;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class EqualAndHashCode {

    public static void main(String[] args) {
        testIntegerEqual();
    }
    private static void  testIntegerEqual() {
        Integer two = new Integer(2);
        Integer kong = null;
        if (two.equals(kong)) {
            System.out.println("ture");
        }
    }
}
