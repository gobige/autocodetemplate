package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CompletionService 【批量提交异步任务】，实现原理也是内部维护了一个【阻塞队列】，默认 无界队列
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService(executor);
        List<Future<String>> futures = new ArrayList<>();

        futures.add(service.submit(() -> getInsPrice(1)));
        futures.add(service.submit(() -> getInsPrice(2)));
        futures.add(service.submit(() -> getInsPrice(3)));

        try {
            for (int i = 0; i < 3; i++) {
                // 当任务执行结束就把任务的执行结果加入到阻塞队列中  空队列take会阻塞
                String result = service.take().get();

                // 先到报价，先使用
                if (result != null) {
                    executor.execute(() -> System.out.println("保存保险报价--" + result));
                    break;
                }
            }
        }finally {
            // 取消其他请求
            int size = futures.size();
            for (int i = 0; i < size; i++) {
                futures.get(i).cancel(true);
            }
        }


        executor.shutdown();
    }

    public static String getInsPrice(Integer type) throws Exception {
        Thread.sleep(1000L);
        switch (type) {
            case 1:
                return "平安报价 2000";
            case 2:
                return "太平洋报价 3000";
            case 3:
                return "人保报价 1000";
            default:
                return "error";
        }
    }
}
