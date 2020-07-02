package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin使用 MapReduce思想 分而治之 处理数据
 */
public class ForkJoinTaskTest {
    public static void main(String[] args) {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world", "hello fork", "hello join",
                "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork", "hello join", "fork join in world",
                "hello fork", "hello join", "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork",
                "hello join", "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork", "hello join",
                "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork", "hello join", "fork join in world",
                "hello fork", "hello join", "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork",
                "hello join", "fork join in world", "hello fork", "hello join", "fork join in world", "hello fork", "hello join",
                "fork join in world"};

        // 创建forkjoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(12);

        StaticTask task = new StaticTask(fc, 0, fc.length);

        Map<String, Long> wordStatics = forkJoinPool.invoke(task);

        wordStatics.forEach((k, v) -> {
            System.out.println(k + "---" + v);
        });
    }
}

class StaticTask extends RecursiveTask<Map<String, Long>> {
    private String[] fc;
    private int start, end;

    StaticTask(String[] fc, int start, int end) {
        this.fc = fc;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Map<String, Long> compute() {
        int i = end - start;
        if (i == 1) {
            return calc(fc[start]);
        }else {
            int mid = i / 2;
            StaticTask task = new StaticTask(fc, start, mid);
            task.fork();
            StaticTask task2 = new StaticTask(fc, mid, end);

            return merge(task2.compute(), task.join());
        }
    }

    private Map<String, Long> merge(Map<String, Long> task1, Map<String, Long> task2) {
        Map<String, Long> result = new HashMap<>();
        result.putAll(task1);

        task2.forEach((k, v) -> {
            Long count = result.get(k);
            if (count != null) {
                result.put(k, count + v);
            }else {
                result.put(k, v);
            }
        });

        return result;
    }

    private Map<String, Long> calc(String word) {
        Map<String, Long> result = new HashMap<>();
        String[] split = word.split(",");

        int length = split.length;
        for (int i = 0; i < length; i++) {
            Long count = result.get(split[i]);
            if (count == 0) {
                result.put(split[i], 1L);
            }else {
                result.put(split[i], count++);
            }
        }

        return result;
    }
}