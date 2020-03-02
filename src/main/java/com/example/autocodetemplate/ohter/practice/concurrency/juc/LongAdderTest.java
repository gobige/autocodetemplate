package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import org.springframework.util.StopWatch;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    private static AtomicLong atomicLong = new AtomicLong(0);
    private static LongAdder longAdder = new LongAdder();
    private static final Integer cycleCount = 100;
    private static final Integer threadNum = 2;

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("atomicLong");
        atomicLongIncrease();
        stopWatch.stop();
        stopWatch.start("longAdder");
        longAdderIncrease();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public static void atomicLongIncrease() {

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < cycleCount ; j++) {
                        atomicLong.incrementAndGet();                    }
                }
            }).start();
        }
    }
    public static void longAdderIncrease() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < cycleCount ; j++) {
                        longAdder.add(1L);
                }
            }}).start();
        }
    }
}
