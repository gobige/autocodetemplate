package com.example.autocodetemplate.ohter.practice.dataStructrue;

import sun.misc.Unsafe;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class outOfMemTest {
    public static void main(String[] args) throws Exception {
        new outOfMemTest().testDumpOutMem();
//        new outOfMemTest().testStackOverFlow();
//        new outOfMemTest().testOutMemByCreateThread();
//        new outOfMemTest().testConstantOutMem();
//        new outOfMemTest().testDirectMemOut();
//        new outOfMemTest().testTool();


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
     * 测试堆内存溢出 -Xms20m -Xmx20m -XX:+PrintGCDetails  -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\temporary
     */
    public void testDumpOutMem() {
        List<OomObject> list = new ArrayList<OomObject>();
        while (true) {
            list.add(new OomObject());
        }
    }

    int stackLength  = 0;
    /**
     * 测试方法栈泄漏 -Xss2m
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
     * 1.6 -XX:PermSize1M -XX:MaxPermSize1M
     * 1.7以后运行时常量池在堆内存
     * 常量池内存溢出 -Xms10m -Xmx10m -XX:+PrintGCDetails  -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\temporary
     */
    public void testConstantOutMem() {
        List<String> list = new ArrayList<String>();
        Integer i = 1;
        while (true) {
            list.add(new String(i++ + "")); // GC overhead limit exceeded  如果GC花费的时间超过 98%, 并且GC回收的内存少于 2%,  -XX:-UseGCOverheadLimit去掉限制
//            list.add(String.valueOf(i++).intern());
        }
    }

    private static final int _1MB = 1024 * 1024;
    /**
     * 测试直接内存溢出 -XX:MaxDirectMemorySize=10M
     */
    public void testDirectMemOut() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    /**
     * reference test
     */
    public void reference(){
        SoftReference<OomObject> soft = new SoftReference<OomObject>(new OomObject());

    }
}
class OomObject{

}