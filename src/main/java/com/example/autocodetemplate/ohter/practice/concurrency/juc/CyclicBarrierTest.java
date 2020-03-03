package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 多个线程等待某个事件达成
 * 可重复进行
 */
@Slf4j
public class CyclicBarrierTest {
    private static final int CYCLE_COUNT = 200;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
        @Override
        public void run() {
            log.info("达到漂流最小乘坐人数，出发！");
        }
    });

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < CYCLE_COUNT; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info("{}进入漂流船中", Thread.currentThread().getName());
                    try {
                        int arrivelNum = cyclicBarrier.await();
                        log.info("{}高兴的叫了起来{}", Thread.currentThread().getName(), arrivelNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.setName("游客--" + i);
            thread.start();
            Thread.sleep(1000L);
        }
    }

}