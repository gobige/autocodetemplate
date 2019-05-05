package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.service.DataBaseTableService;
import com.example.autocodetemplate.sharding.Enum.EnumDateShardTableType;
import com.example.autocodetemplate.sharding.Enum.EnumPoolManageType;
import com.example.autocodetemplate.util.TableNameUtils;
import com.example.autocodetemplate.util.TimeUtil;
import com.example.autocodetemplate.util.sqlUtil;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务类
 */
@Component
public class SchedulingTaskController {
    private static Logger logger = LoggerFactory.getLogger(sqlUtil.class);

    public static Map<Integer, String[]> testHashMap = new HashMap<>();
    public static Integer addNum = 1;

    @Resource
    private DataBaseTableService dataBaseTableService;


    /**
     * fixedRate 上一次开始执行点时间计算
     * fixedDelay 上一次结束执行点时间计算
     * initialDelay 初始化时延迟时间，执行，之后正常执行
     * cron="" 根据cron表达式配置执行计划
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void reportFindGirl() {
        System.out.println("七夕节到了，还不找女朋友，你妈又要催了!"+ TimeUtil.dateToLocalDateTime(new Date()));
        System.out.println("测试热部署 ctrl+F9!!!"+ TimeUtil.dateToLocalDateTime(new Date()));
        testHashMap.put(addNum, new String[100]);
    }


    @Scheduled(cron = "0/30 * * * * ?")
    public void createTable() {
        createTable("full_text_test", EnumDateShardTableType.MONTHLY_TABLE, EnumPoolManageType.YATES_TEST_DB_1, null);
    }


    /**
     * 根据不同表类型创建日期拆分表
     *
     * @param baseTableName  模板表
     * @param type           按日期拆分表类型
     * @param poolManageType 连接数据库类型
     */
    public void createTable(java.lang.String baseTableName, EnumDateShardTableType type, EnumPoolManageType poolManageType, Date date) {
        if (StringUtils.isBlank(baseTableName)) {
            logger.error("创建日期拆分表失败---传入参数错误---模板表为空");
            return;
        }
        if (type == null || poolManageType == null) {
            logger.error("创建日期拆分表失败---传入参数错误---EnumDateShardTableType:[{}],EnumPoolManageType:[{}]", type, poolManageType);
            return;
        }

        try {
            java.lang.String tableName = null;

            switch (type) {
                case MONTHLY_TABLE:
                    // 默认生成下个月的月表
                    if (date == null) {
                        LocalDate curLocalDate = TimeUtil.dateToLocalDate(new Date());
                        LocalDate nextLocalDate = curLocalDate.plusMonths(1);
                        date = TimeUtil.localDateToDate(nextLocalDate);
                    }

                    tableName = baseTableName + TableNameUtils.getDateSuffix(date, type);
                    break;
                case DAILY_TABLE:
                    // 默认生成明天的日表
                    if (date == null) {
                        LocalDate curLocalDate = TimeUtil.dateToLocalDate(new Date());
                        LocalDate nextLocalDate = curLocalDate.plusDays(1);
                        date = TimeUtil.localDateToDate(nextLocalDate);
                    }

                    tableName = baseTableName + TableNameUtils.getDateSuffix(date, type);
                    break;
                default:
                    logger.error("按日期分表失败---暂不支持格式按时间分表格式！！");
                    break;
            }

            createTable(baseTableName, tableName, poolManageType);
        } catch (Exception e) {
            logger.error("按日期分表失败---{}", e);
        }
    }

    /**
     * 按时间拆分表
     * @param baseTableName 模板表
     * @param tableName 新建表名
     * @param poolManageType 连接数据库
     */
    public void createTable(java.lang.String baseTableName, java.lang.String tableName, EnumPoolManageType poolManageType) {
        int creNum = 0;
        try {
            switch(poolManageType) {
                // 根据不同poolManageType获取不同数据库连接
                case WST_CARHOUSE:
                    break;
                case WST_FINANCE:
                    break;
                case YATES_TEST_DB_1:
                    creNum = dataBaseTableService.createTableStruByCopy(tableName, baseTableName);
                    break;
                case YATES_TEST_DB_2:
                    creNum =dataBaseTableService.createTableStruByCopy(tableName, baseTableName);
                    break;
                default:
                    logger.error("按日期分表失败---暂不支持该数据库下的生成表！！");
                    break;
            }
        } catch (Exception e) {
            logger.info("创建表[{}]失败！！mes:", tableName, e.getMessage());
        }

        if (creNum == 0) {
            logger.info("创建表[{}]失败！！", tableName);
        }
    }

}
