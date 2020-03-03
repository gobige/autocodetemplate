package com.example.autocodetemplate.ohter.practice.concurrency.juc;

import com.example.autocodetemplate.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列提供了在指定时间才能获取队列元素的功能，队列头元素是最接近过期的元素
 * 延时队列不能存放空元素
 * iterator()遍历顺序不保证是元素的实际存放顺序
 */
@Slf4j
public class DelayQueueTest {

    private static DelayQueue delayQueue = new DelayQueue();

    public static final Integer taskCount = 10;

    public static void main(String[] args) {
        consume();
        deque();
    }

    private static void consume() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    DelayTask task = (DelayTask) delayQueue.poll();
                    if (task != null) {
                        task.excute();
                    }
                }
            }
        });

        thread.setName("consumer--");
        thread.start();
    }

    private static void deque() {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        for (int i = 0; i < taskCount; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    delayQueue.offer(new DelayTask(Thread.currentThread().getName(), TimeUtil.localDateTimeToDate(now.plusSeconds((long) (Math.random() * 10)))));

                    log.info("{}任务被加入", Thread.currentThread().getName());
                }
            });

            thread.setName("task--" + i);
            thread.start();
        }
    }
}

@Slf4j
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
        log.info("时间{}，{}任务被执行", this.time, this.name);
    }
}