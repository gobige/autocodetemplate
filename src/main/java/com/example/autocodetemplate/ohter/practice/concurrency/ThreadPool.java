package com.example.autocodetemplate.ohter.practice.concurrency;

public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorker(int num);

    void removeWorker(int count);

    int getJobSize();
}
