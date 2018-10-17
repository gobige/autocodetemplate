package com.example.autocodetemplate.ohter.practice.base;

/**
 * 递归，类方法，普通方法调用执行时间测试demo(无法得出结论，位于前面的方法，因为第一次调用，调用时间要长一些)
 */
public class MethodTransferTimeTest {
    public static void main(String[] args) {
        MethodTransferTimeTest transferTimeTest = new MethodTransferTimeTest();

        transferTimeTest.testRecursive(5000);


        long RecursivestartTime = System.currentTimeMillis();
        transferTimeTest.testRecursive(5000);
        long RecursiveendTime = System.currentTimeMillis();

        long startTime = System.currentTimeMillis();
        transferTimeTest.testSingleMethod();
        long endTime = System.currentTimeMillis();

        long classStartTime = System.currentTimeMillis();
        testStaticSingleMethod();
        long classEndTime = System.currentTimeMillis();

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
