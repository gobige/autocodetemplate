package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.controller.vo.AutoFillSqlVO;
import com.example.autocodetemplate.util.TimeUtil;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务类
 */
@Component
public class SchedulingTaskController {

    public static Map<Integer, String[]> testHashMap = new HashMap<>();
    public static Integer addNum = 1;
    /**
     * fixedRate 上一次开始执行点时间计算
     * fixedDelay 上一次结束执行点时间计算
     * initialDelay 初始化时延迟时间，执行，之后正常执行
     * cron="" 根据cron表达式配置执行计划
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void reportFindGirl() {
        System.out.println("七夕节到了，还不找女朋友，你妈又要催了!"+ TimeUtil.dateToLocalDateTime(new Date()));
        System.out.println("测试热部署 ctrl+F9!!!"+ TimeUtil.dateToLocalDateTime(new Date()));
        testHashMap.put(addNum, new String[100]);
    }

    public static void main(String[] args) {
        AutoFillSqlVO vo = new AutoFillSqlVO();
    }
}
