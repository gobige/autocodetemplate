package com.example.autocodetemplate.ohter.practice.base;

import java.util.Random;

public class FinalTest {
    private static Random rand = new Random(47);
    private String id;

    public FinalTest(String id) {
        this.id = id;
    }

    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;
    public static final int VALUE_THREE = 23;
    private final int i4 = rand.nextInt(20);
    static final int INT_5 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value val_3 = new Value(33);
    private final int[] a = {1,2,3,4,5,6};

    public String toString() {
        return id + ": "+"14 = " + i4 + ",INT_5 = " + INT_5;
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest("finaltest");
        // finalTest.valueOne ++; 编译报错，final修饰基本类型赋值后不可变
        finalTest.v2.i++; // final修辞引用对象，引用不可变，但对象类变量可变
        // finalTest.v2 = new Value(9); 编译报错 final修辞引用对象，引用不可变，
        // FinalTest.val_3 = new Value(9); 编译报错 final修辞引用对象，引用不可变，
        finalTest.v1 = new Value(9); // 没有final修辞，可变
        finalTest.a[2]++; // final修辞数组中元素可变
        // finalTest.a = {2,3};  final修辞数组引用不可变
        System.out.println(finalTest);
        FinalTest finalTest2 = new FinalTest("finaltest");
        System.out.println(finalTest);
        System.out.println(finalTest2);


    }


}

class Value {
    int i;

    public Value(int i) {
        this.i = i;
    }
}
