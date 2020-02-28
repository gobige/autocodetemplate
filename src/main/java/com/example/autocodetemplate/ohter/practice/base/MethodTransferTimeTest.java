package com.example.autocodetemplate.ohter.practice.base;

import org.springframework.util.StopWatch;

/**
 * 递归：因为层次太深会造成栈溢出，但是明显时间慢于其他方式调用
 * 类方法，和普通方法一样快
 * 普通方法：和静态方法一样快
 * 调用执行时间测试demo(无法得出结论，位于前面的方法，因为第一次调用，调用时间要长一些)
 */
public class MethodTransferTimeTest {
    public static void main(String[] args) {
        MethodTransferTimeTest transferTimeTest = new MethodTransferTimeTest();

        int count = 10000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("recursive test");
        transferTimeTest.testRecursive(count);
        stopWatch.stop();

        stopWatch.start("circulation test");
        transferTimeTest.testSingleMethod(count);
        stopWatch.stop();

        stopWatch.start("static method circulation test");
        testStaticSingleMethod(count);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    public void testRecursive(int num) {
        if (num == 1) {
        } else {
            testRecursive(num - 1);
        }
    }

    public static final void testStaticSingleMethod(int count) {
        for (int i = 0;i < count;i++) {
        }

    }

    public void testSingleMethod(int count) {
        for (int i = 0;i < count;i++) {
        }

    }
}
