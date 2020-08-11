package com.example.autocodetemplate.ohter.practice.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadLocalTest {

    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) throws Exception {

        testCallable testCallable = new testCallable();

        FutureTask futureTask = new FutureTask(testCallable);

        new Thread(futureTask).start();

        System.out.println(futureTask.get());

    }

}

class testCallable implements Callable{
    @Override
    public Object call() throws Exception {

        System.out.println("callable test");
        return new Object();
    }
}