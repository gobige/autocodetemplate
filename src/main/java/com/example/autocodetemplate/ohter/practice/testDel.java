package com.example.autocodetemplate.ohter.practice;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class testDel {
    public static void main(String[] args) {
        long classStartTime = System.currentTimeMillis();
        testStaticSingleMethod();
        long classEndTime = System.currentTimeMillis();

        testDel testDel = new testDel();
        long startTime = System.currentTimeMillis();
        testDel.testSingleMethod();
        long endTime = System.currentTimeMillis();


        long RecursivestartTime = System.currentTimeMillis();
        testDel.testRecursive(5000);
        long RecursiveendTime = System.currentTimeMillis();
        System.out.println("递归方法执行时间" + (RecursiveendTime - RecursivestartTime));
        System.out.println("类方法执行时间" + (classEndTime - classStartTime));
        System.out.println("普通方法执行时间" + (endTime - startTime));

    }

    public void testRecursive(int num) {
        if (num == 1) {
            System.out.println("递归方法调用次数" + num);
        } else {
            System.out.println("递归方法调用次数" + num);
            testRecursive(num - 1);
        }
    }

    public static final void testStaticSingleMethod() {
        for (int i = 0;i < 5000;i++) {
            System.out.println("类方法调用次数" + i);
        }

    }

    public void testSingleMethod() {
        for (int i = 0;i < 5000;i++) {
            System.out.println("普通调用次数" + i);
        }

    }
}

class test1 extends test implements testInteface {

}

class test implements testInteface {

}

interface testInteface {
    static String test() {
        return "test interface";
    }
}


