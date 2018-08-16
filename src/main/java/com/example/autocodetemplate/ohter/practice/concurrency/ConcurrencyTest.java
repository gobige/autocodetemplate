package com.example.autocodetemplate.ohter.practice.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrencyTest {
    private static final long count = 10000000000l;
    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();

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
