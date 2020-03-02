package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 一个线程等待多个线程事件达成
 */
@Slf4j
public class CountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws Exception  {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(countDownLatchTest.new CookRice());
        executor.execute(countDownLatchTest.new SpicyChicken());

        countDownLatch.await();// 是await不是wait
        log.info("饭菜都有了，开饭开饭！！！", Thread.currentThread().getName());
    }



    class SpicyChicken implements Runnable{
        @Override
        public void run() {
            log.info("开始炒辣子鸡丁");
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
            }
            log.info("辣子鸡丁炒好了");
            countDownLatch.countDown();
        }
    }

    class CookRice implements Runnable{
        @Override
        public void run() {
            log.info("开始煮米饭");
            try {
                Thread.sleep(3000L);
            } catch (Exception e) {
            }
            log.info("米饭熟了");
            countDownLatch.countDown();
        }
    }

}
