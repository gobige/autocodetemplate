package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {

    private static ReentrantLock reentrantLock = new ReentrantLock(false);
    private static Condition condition = reentrantLock.newCondition();

    private static final int CYCLE_COUNT = 1000;
    private static final long SLEEP_TIME = 2000L;


    public static void main(String[] args) throws  Exception{
        sync();
    }

    private static void sync() throws Exception{
        for (int i = 0; i < CYCLE_COUNT; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info("{}尝试获取锁", Thread.currentThread().getName());
                    while (true) {
                        if (reentrantLock.tryLock()) {
                            log.info("{}第一次获取到锁", Thread.currentThread().getName());

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                reentrantLock.unlock();
                            }

                            break;
                        }
                    }

                    while (true) {
                        if (reentrantLock.tryLock()) {
                            log.info("{}第二次获取到锁", Thread.currentThread().getName());
//                            log.info("同步锁信息：当前同步队列长度【{}】，hold数量为【{}】等待队列长度【{}】", reentrantLock.getQueueLength(), reentrantLock.getHoldCount(), reentrantLock.getWaitQueueLength(condition));

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                reentrantLock.unlock();
                            }
                            break;
                        }
                    }

                    log.info("{}完成任务执行！", Thread.currentThread().getName());
                }
            });

            thread.setName("thread--" + i);
            thread.start();
        }
    }
}
