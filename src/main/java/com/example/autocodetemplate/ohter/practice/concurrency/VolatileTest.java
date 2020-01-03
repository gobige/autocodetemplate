package com.example.autocodetemplate.ohter.practice.concurrency;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class VolatileTest {
    public static volatile Integer  shareNum = 0;

    public static void main(String[] args) {
        new showNum().start();
        new increNum().start();
    }

}

class increNum extends Thread {
    @Override
    public void run() {
        int localNum = VolatileTest.shareNum;
        while (VolatileTest.shareNum < 5) {
            System.out.println("incre shareNum value  " + localNum);

            VolatileTest.shareNum = ++localNum;

            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
    }
}

// 显示共享变量的值，如果共享变量为volatile修辞，表示该变量读写时会重新从主内存再次获取；若无volatile修辞，将得不到最新共享变量的值，而是直接cpu cache的值
class showNum extends Thread {
    @Override
    public void run() {
        int localNum = VolatileTest.shareNum;
        while (localNum < 5) {

            if (localNum != VolatileTest.shareNum) {
                System.out.println("current shareNum value is " + localNum);
                localNum = VolatileTest.shareNum;
            }

            try {
                // 线程睡眠会让出cpu，有机会把最新的数据从主内存同步  cpu cache
                Thread.sleep(5);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}