package com.example.autocodetemplate.ohter.practice.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockTest {
    private static ReentrantLock miaoSha = new ReentrantLock(true);
    private AtomicInteger goodsNum;
    private Semaphore semaphore;

    ReetrantLockTest(Integer goodsNum) {
        this.goodsNum = new AtomicInteger(goodsNum);
        this.semaphore = new Semaphore(goodsNum + 100, true);
    }

    public void exeute() {
        if (semaphore.tryAcquire()) {

        }else {
            System.out.println("当前可允许进入抢购人数已达上限！！");
        }
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 100; i++) {

        }
    }
}
