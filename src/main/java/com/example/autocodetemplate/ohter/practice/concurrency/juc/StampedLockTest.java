package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockTest {
    private static StampedLock stampedLock = new StampedLock();

    private static final int READ_LOCK_CYCLE_COUNT = 1000;
    private static final int WIRTE_LOCK_CYCLE_COUNT = 5;
    private static final long SLEEP_TIME = 2000L;

    public static void main(String[] args) throws Exception {
        writeLock();
        readLock();
    }


    private static void readLock() throws Exception{
        for (int i = 0; i < READ_LOCK_CYCLE_COUNT; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (stampedLock.tryReadLock() > 0) {
                            log.info("{}获取到读锁", Thread.currentThread().getName());
                            log.info("同步锁信息：readCount数量为【{}】", stampedLock.getReadLockCount());

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                stampedLock.tryUnlockRead();
                                log.info("{}释放读锁！", Thread.currentThread().getName());
                            }

                            break;
                        }
                    }
                }
            });

            thread.setName("read thread--" + i);
            thread.start();
        }
    }
    private static void writeLock() throws Exception{
        for (int i = 0; i < WIRTE_LOCK_CYCLE_COUNT; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (stampedLock.writeLock() >0) {
                            log.info("{}获取到写锁", Thread.currentThread().getName());
                            log.info("同步锁信息：readCount数量为【{}】", stampedLock.getReadLockCount());

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                stampedLock.tryUnlockWrite();
                                log.info("{}释放写锁！", Thread.currentThread().getName());
                            }
                            break;
                        }
                    }

                }
            });

            thread.setName("write thread--" + i);
            thread.start();
        }
    }
}
