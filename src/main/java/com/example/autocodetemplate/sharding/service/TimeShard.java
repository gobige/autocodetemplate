package com.example.autocodetemplate.sharding.service;

import com.example.autocodetemplate.service.DataBaseTableService;
import com.example.autocodetemplate.sharding.Enum.EnumDateShardTableType;
import com.example.autocodetemplate.sharding.Enum.EnumPoolManageType;
import com.example.autocodetemplate.util.TableNameUtils;
import com.example.autocodetemplate.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
public class TimeShard {

    @Resource
    private DataBaseTableService dataBaseTableService;

    /**
     * 根据不同表类型创建日期拆分表
     *
     * @param baseTableName  模板表
     * @param type           按日期拆分表类型
     * @param poolManageType 连接数据库类型
     */
    public void createTable(java.lang.String baseTableName, EnumDateShardTableType type, EnumPoolManageType poolManageType, Date date) {
        if (StringUtils.isBlank(baseTableName)) {
            log.error("创建日期拆分表失败---传入参数错误---模板表为空");
            return;
        }
        if (type == null || poolManageType == null) {
            log.error("创建日期拆分表失败---传入参数错误---EnumDateShardTableType:[{}],EnumPoolManageType:[{}]", type, poolManageType);
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
                    log.error("按日期分表失败---暂不支持格式按时间分表格式！！");
                    break;
            }

            createTable(baseTableName, tableName, poolManageType);
        } catch (Exception e) {
            log.error("按日期分表失败---{}", e);
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
                    log.error("按日期分表失败---暂不支持该数据库下的生成表！！");
                    break;
            }
        } catch (Exception e) {
            log.info("创建表[{}]失败！！mes:", tableName, e.getMessage());
        }

        if (creNum == 0) {
            log.info("创建表[{}]失败！！", tableName);
        }
    }
}
