package com.example.autocodetemplate.ohter.practice.concurrency;

import com.example.autocodetemplate.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class ConcurrencyTest {
    private static final long count = 10000000000l;
    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static void main(String[] args) throws InterruptedException {
//        concurrency();
//        serial();
         testMultipeThreadUserMethod();

    }

    private static synchronized void testMethod(String threadName) {
        System.out.println("comn in method,thread " + threadName);
        SleepUtils.second(5);
        System.out.println("go out method,thread " + threadName);
    }

    private static void testMultipeThreadUserMethod() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyTest.testMethod("Thread1");
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyTest.testMethod("thread2");
            }
        });
        thread2.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyTest.testMethod("thread3");
            }
        });
        thread3.start();
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyTest.testMethod("thread4");
            }
        });
        thread4.start();
    }
    /**
     * 多线程执行
     *
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        logger.info("concurrency handle spend:{}ms,event[{}]", time, b);
    }

    /**
     * 单线程执行
     *
     * @throws InterruptedException
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        logger.info("serial handle spend:{}ms,eventb[{}],eventa[{}]", time, b, a);
    }
}
