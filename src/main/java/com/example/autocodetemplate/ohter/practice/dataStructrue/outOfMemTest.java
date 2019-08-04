package com.example.autocodetemplate.ohter.practice.dataStructrue;

import java.util.ArrayList;
import java.util.List;

public class outOfMemTest {
    public static void main(String[] args) {
//        new outOfMemTest().testDumpOutMem();
//        new outOfMemTest().testStackOverFlow();
//        new outOfMemTest().testOutMemByCreateThread();
//        new outOfMemTest().testConstantOutMem();
        new outOfMemTest().testTool();



    }

    private void testTool() {
        int i = 0;
        while (true) {
            try {
                Integer[] test = new Integer[1024 * i];

                Thread.sleep(1000);
                i++;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 测试堆内存溢出 -Xms -Xmx
     */
    public void testDumpOutMem() {
        List<outTestObj> list = new ArrayList<outTestObj>();
        while (true) {
            list.add(new outTestObj());
        }
    }

    int stackLength  = 0;
    /**
     * 测试方法栈泄漏 -Xss
     */
    public void testStackOverFlow() {
        System.out.println(stackLength);
        stackLength ++;
        testStackOverFlow();
    }

    /**
     * 测试线程创建内存溢出(慎用)
     */
    public void testOutMemByCreateThread() {
        while (true) {
           Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                   dontStop();
               }
           });
           thread.start();
        }
    }

    public void dontStop() {
        while (true) {}
    }

    /**
     * 常量池内存溢出 -XX:PermSize=10M -XX:MaxPermSize=10M
     */
    public void testConstantOutMem() {
        List<String> list = new ArrayList<String>();
        Integer i = 1;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    private static final int _1MB = 1024 * 1024;
    /**
     * 测试直接内存溢出 -XX:MaxDirectMemorySize=10M
     */
//    public void testDirectMemOut() {
//        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
//        unsafeField.setAccessible(true);
//        Unsafe unsafe = (Unsafe)unsafeField.get(null);
//        while (true) {
//            unsafe.allocateMemory(_1MB);
//        }
//    }
}

class outTestObj {

        }