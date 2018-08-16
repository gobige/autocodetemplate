package com.example.autocodetemplate.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 */
@Component
public class SchedulingTaskController {

    /**
     * fixedRate 上一次开始执行点时间计算
     * fixedDelay 上一次结束执行点时间计算
     * initialDelay 初始化时延迟时间，执行，之后正常执行
     * cron="" 根据cron表达式配置执行计划
     */
    @Scheduled(fixedRate = 4000)
    public void reportFindGirl() {
        System.out.println("七夕节到了，还不找女朋友，你妈又要催了");
    }
}
