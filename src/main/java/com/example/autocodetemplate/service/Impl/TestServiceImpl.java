package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    @Async
    public Future<String> doTaskOne() throws Exception {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始第一个任务");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("第一个任务完成");
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return new AsyncResult<>("任务1完成,耗时:" + (endTime - startTime));
    }

    @Override
    public Future<String> doTaskTwo() throws Exception {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始第二个任务");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("第二个任务完成");
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return new AsyncResult<>("任务2完成,耗时:" + (endTime - startTime));
    }

    @Override
    @Async
    public Future<String> doTaskThree() throws Exception {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始第仨个任务");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("第仨个任务完成");
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return new AsyncResult<>("任务3完成,耗时:" + (endTime - startTime));
    }
}
