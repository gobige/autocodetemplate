package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExchanger {
    private static final Exchanger<String> exc = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                String A = "银行流水a";
                try {
                    System.out.println("A thread start change");
                    exc.exchange(A);
                    System.out.println("A thread end change");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                String b = "银行流水B";
                try {
                    System.out.println("B thread start change");
                    String a = exc.exchange("");
                    System.out.println("B thread end change");
                    System.out.println(a + ":" + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
