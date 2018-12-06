package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    @Scheduled(cron = "0 0 2 1 * ?")
    public void reportFindGirl() {
        System.out.println("七夕节到了，还不找女朋友，你妈又要催了!"+ TimeUtil.dateToLocalDateTime(new Date()));
    }
}
