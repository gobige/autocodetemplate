package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockTest {

    private static final int READ_LOCK_CYCLE_COUNT = 10;
    private static final int WIRTE_LOCK_CYCLE_COUNT = 5;
    private static final long SLEEP_TIME = 2000L;

    public static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    public static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    public static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    public static void main(String[] args) throws Exception {
        readLock();
        writeLock();
    }
    private static void readLock() throws Exception{
        for (int i = 0; i < READ_LOCK_CYCLE_COUNT; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (readLock.tryLock()) {
                            log.info("{}获取到读锁", Thread.currentThread().getName());
                            log.info("同步锁信息：当前readhold数量为【{}】，writehold数量为【{}】readCount数量为【{}】", readWriteLock.getReadHoldCount(), readWriteLock.getWriteHoldCount(), readWriteLock.getReadLockCount());

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                readLock.unlock();
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
                        if (writeLock.tryLock()) {
                            log.info("{}获取到写锁", Thread.currentThread().getName());
                            log.info("同步锁信息：当前readhold数量为【{}】，writehold数量为【{}】readCount数量为【{}】", readWriteLock.getReadHoldCount(), readWriteLock.getWriteHoldCount(), readWriteLock.getReadLockCount());

                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {

                            } finally {
                                writeLock.unlock();
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
