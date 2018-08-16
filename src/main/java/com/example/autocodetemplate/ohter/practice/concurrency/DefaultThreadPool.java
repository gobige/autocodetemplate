package com.example.autocodetemplate.ohter.practice.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Runnable> {

    // 最大可支持线程数
    private static final int MAX_WORKER_NUMBER = 10;
    // 默认线程数
    private static final int DEFAULT_WORKERNUMBER = 5;
    // 最小线程数
    private static final int MIN_WORKER_NUMBER = 2;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum = DEFAULT_WORKERNUMBER;
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKERNUMBER);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBER ? MAX_WORKER_NUMBER : num < MIN_WORKER_NUMBER ? MIN_WORKER_NUMBER : num;
        initializeWorkers(workerNum);
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable != null) {
            synchronized (jobs) {
                jobs.addLast((Job) runnable);
            }
        }
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addWorker(int num) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeWorker(int count) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getJobSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "worker-thread:" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            if (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    job = jobs.removeFirst();

                    if (job != null) {
                        job.run();
                    }
                }
            }
        }

        public void shutDown() {
            running = false;
        }
    }
}


class Job implements Runnable {
    @Override
    public void run() {

    }
}