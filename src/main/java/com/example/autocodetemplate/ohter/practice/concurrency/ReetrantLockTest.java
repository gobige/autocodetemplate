package com.example.autocodetemplate.ohter.practice.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReetrantLockTest {
    private ReentrantLock miaoSha = new ReentrantLock(true);
    private  AtomicInteger goodsNum;
    // 同时可请求数量
    private Semaphore semaphore;

    ReetrantLockTest(Integer goodsNum) {
        this.goodsNum = new AtomicInteger(goodsNum);
        this.semaphore = new Semaphore(goodsNum + 10, true);
    }

    public void exeute(String name) throws Exception{
        if (semaphore.tryAcquire()) {
            log.info("线程{}获取到信号量，请求准入", name);
            Thread.sleep(2000L);

            int remain =  goodsNum.intValue();
            if (remain > 0) {
                // redis，数据库 代替
                boolean success = goodsNum.compareAndSet(remain, remain - 1);
//                int minusRemain = goodsNum.decrementAndGet();
                if (success) {
                    log.info("现有商品库存：【{}】，请求线程【{}】扣减商品数量1", remain, name);
                    // 创建订单，其他业务逻辑 todo
                }else {
                    log.info("现有商品库存：【{}】，扣减商品失败！预期库存和实际库存不符合", remain);
                }
            }else {
                log.info("商品库存为0！！！");
            }
        }else {
            log.info("当前可允许进入抢购人数已达上限！！");
        }
    }

    /**
     * 100个请求，准入20个，先到的10个线程抢购商品，支付，抢完为止，秒杀方案
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ReetrantLockTest test = new ReetrantLockTest(10);
        for (int i = 0; i < 100; i++) {
            MyThread myThread = new MyThread(test);
            log.info("当前请求线程：{}", myThread.getName());
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