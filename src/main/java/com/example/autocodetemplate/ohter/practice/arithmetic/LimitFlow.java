package com.example.autocodetemplate.ohter.practice.arithmetic;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class LimitFlow {

    private static AtomicLong request = new AtomicLong(0);

    private static long Threshold = 1000L;
    private static long fistTime = System.currentTimeMillis();

    public static void main(String[] args)throws Exception {

//        countTest();
        ratelimit();
    }

    /**
     * 令牌桶算法
     * @throws Exception
     */
    public static void ratelimit() throws Exception{
        RateLimiter rateLimiter = RateLimiter.create(10);
        for (int i = 0; i < 1000; i++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 阻塞获取
                    System.out.println("获取到令牌：" + rateLimiter.acquire(1));
//                    // 获取失败返回false
//                    System.out.println("获取令牌：" + rateLimiter.tryAcquire(1));
//                    // 超时获取
//                    System.out.println("超时获取令牌：" + rateLimiter.tryAcquire(1, 100, TimeUnit.MILLISECONDS));
                }
            });

            Thread.sleep(10L);
            thread.start();
            thread.setName("Thread " + i);
        }
    }

    public static void countTest() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "请求某URL：" + countRequest());
                }
            });
            Thread.sleep(1);
            thread.start();
            thread.setName("Thread " + i);
        }
    }

    /**
     * 计数器限流  会有临界问题
     * @return
     */
    public static boolean countRequest() {
        long curTime = System.currentTimeMillis();

        if (curTime - fistTime > Threshold) {
            request.set(1);
            fistTime = curTime;
            System.out.println("时间界限过期，计数器归零");
            return true;
        }

        if (request.get() > 99) {
            return false;
        }

        request.incrementAndGet();

        return true;
    }
}
