package com.example.autocodetemplate.controller;

import java.time.*;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 通过JDK自带工具实现一个间隔并发执行的定时任务；
 */
public class ScheduleTask {

    private static DelayQueue delayQueue = new DelayQueue();

    public static final long INTERVAL_TIME = 1000L;

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("任务被创建");
                        Thread.sleep(INTERVAL_TIME);
                        delayQueue.offer(new DelayTask(Thread.currentThread().getName(), new Date()));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        producerThread.setName("producer");
        producerThread.start();


        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    DelayTask task = (DelayTask) delayQueue.poll();
                    if (task != null) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                task.excute();
                            }
                        });
                        thread.setName("consumer--" + thread.getId());
                        thread.start();
                    }
                }
            }
        });

        consumerThread.start();
    }

}


class DelayTask implements Delayed {
    private String name;
    private Date time;

    public DelayTask(String name, Date time) {
        this.name = name;
        this.time = time;

    }
    @Override
    public long getDelay(TimeUnit unit) {
        return time.getTime() - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayTask delayTask = (DelayTask) o;

        if (this.time.before(delayTask.time)) {
            return -1;
        }else {
            return 1;
        }
    }

    public void excute() {
        System.out.println("时间" + this.time + "任务" + this.name + "被执行");
    }
}