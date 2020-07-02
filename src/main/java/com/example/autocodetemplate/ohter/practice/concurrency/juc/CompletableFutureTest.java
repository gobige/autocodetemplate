package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * CompletableFuture 异步编程
 * 默认使用forkJoin 线程池算法
 *
 * CompletionStage代表异步计算过程中的某一个阶段
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        combineTaskExecution();
    }

    /**
     * 聚合任务执行
     */
    public static void combineTaskExecution() {
        //  Runnable 接口的run()方法没有返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()-> {
            System.out.println("T1:洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1:烧开水...");
            sleep(4, TimeUnit.SECONDS);
        });
        //  Supplier接口的get()方法是有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()-> {
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });


        // 汇聚关系  ->AND聚合；
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf)-> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            int s = 1 / 0;
            return "上茶:" + tf;
        }).whenComplete((r,e) -> {
            System.out.println("客官，进来玩呀！" + r);
        }).exceptionally(e -> {
            return "exception!" + e;
        });


        // 汇聚关系  -> ；OR聚合
//        CompletableFuture<String> f3 = f1.applyToEither(f2, s-> {
//            System.out.println("T1:拿到茶叶:");
//            System.out.println("T1:泡茶...");
//            return "上茶:";
//        }).whenComplete((r,e) -> {
//            System.out.println("客官，进来玩呀！");
//        });

        //等待任务3执行结果
        System.out.println(f3.join());
    }

    /**
     * 串行任务执行
     */
    public static void serialTaskExecution() {
        // 串行执行
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> "起床")
                .thenApply(q -> q + "洗脸").thenApply(x -> x + "吃饭");
        System.out.println(task.join());

        // 串行 异步（多线程）执行
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "起床")
                .thenApplyAsync(q -> q + "洗脸").thenApply(x -> x + "吃饭").thenApplyAsync(c -> c + "刷牙");
        System.out.println(task2.join());

        // 串行 异步（多线程）执行
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> "起床")
                .thenApplyAsync(q -> q + "洗脸").thenAccept(new Consumer<String>() {
                    @Override
                    // thenAccept接收上一阶段的输出作为本阶段的输入
                    public void accept(String s) {
                        System.out.println(s + "吃饭");
                    }
                    // thenRun根本不关心前一阶段的输出
                }).thenRun(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("刷牙");
                    }
                }).thenApply(p -> p + "上学");

        CompletableFuture<String> task4 = task3.thenCompose( i -> {
                return CompletableFuture.supplyAsync(() -> {
                    return i+"上课";
                });
        });

        System.out.println(task4.join());
    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
