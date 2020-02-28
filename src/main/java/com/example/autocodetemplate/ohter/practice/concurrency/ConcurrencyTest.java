package com.example.autocodetemplate.ohter.practice.concurrency;

import com.example.autocodetemplate.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * 多线程因为需要上下文切换，所以这种情况下并不比单线程快
 */
public class ConcurrencyTest {
    private static final long count = 30000000l;
    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("多线程");
        concurrency();
        stopWatch.stop();
        stopWatch.start("单线程");
        serial();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * 方法加锁，多线程竞争
     * @param threadName
     */
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
        Thread thread = new Thread(new Runnable() {
            int a = 0;
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.setName("多线程");
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
    }

    /**
     * 单线程执行
     *
     * @throws InterruptedException
     */
    private static void serial() {
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
    }
}
