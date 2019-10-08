package com.example.autocodetemplate.ohter.practice.concurrency;

import com.example.autocodetemplate.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockTest {
    private static final Logger logger = LoggerFactory.getLogger(ReetrantLockTest.class);
    private ReentrantLock miaoSha = new ReentrantLock(true);
    private  AtomicInteger goodsNum;
    // 同时可请求数量
    private Semaphore semaphore;

    ReetrantLockTest(Integer goodsNum) {
        this.goodsNum = new AtomicInteger(goodsNum);
        this.semaphore = new Semaphore(goodsNum + 100, true);
    }

    public void exeute(String name) throws Exception{
        if (semaphore.tryAcquire()) {
            Thread.sleep(2000L);

            int remain =  goodsNum.intValue();
            if (remain > 0) {
                // sql 代替
                boolean success = goodsNum.compareAndSet(remain, remain - 1);
                if (success) {
                    logger.info("现有商品库存：【{}】，请求线程【{}】扣减商品数量1", remain, name);
                    // 创建订单，其他业务逻辑 todo
                }else {
                    logger.info("现有商品库存：【{}】，扣减商品失败！预期库存和实际库存不符合", remain);
                }
            }else {
                logger.info("商品库存为0！！！");
            }
        }else {
            logger.info("当前可允许进入抢购人数已达上限！！");
        }
    }

    public static void main(String[] args) throws Exception {
        ReetrantLockTest test = new ReetrantLockTest(10);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(500L);
            MyThread myThread = new MyThread(test);
            logger.info("当前请求线程：{}", myThread.getName());
            myThread.start();
        }
        Thread.sleep(100000L);
    }

}

class MyThread extends Thread {
    ReetrantLockTest test;
    MyThread(ReetrantLockTest reetrantLockTest) {
        test = reetrantLockTest;
    }

    @Override
    public void run() {
        try {
            test.exeute(this.getName());
        } catch (Exception e) {
        }
    }
}