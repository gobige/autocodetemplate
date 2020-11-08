package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.sharding.Enum.EnumDateShardTableType;
import com.example.autocodetemplate.sharding.Enum.EnumPoolManageType;
import com.example.autocodetemplate.sharding.service.TimeShard;
import com.example.autocodetemplate.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
/*定时任务类*/
@Component
public class SchedulingTaskController {

    @Resource
    private TimeShard timeShard;


    @Scheduled(cron = "0/30 * * * * ?")
    public void reportFindGirl() {
        System.out.println("定时任务!!"+ TimeUtil.dateToLocalDateTime(new Date()));
    }


    /**
     * fixedRate 上一次开始执行点时间计算
     * fixedDelay 上一次结束执行点时间计算
     * initialDelay 初始化时延迟时间，执行，之后正常执行
     * cron="" 根据cron表达式配置执行计划
     */
    //  @Scheduled(cron = "0/30 * * * * ?",initialDelay = 1000L)
    public void createTable() {
        timeShard.createTable("full_text_test", EnumDateShardTableType.MONTHLY_TABLE, EnumPoolManageType.YATES_TEST_DB_1, null);
    }


}
