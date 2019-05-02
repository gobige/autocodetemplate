package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.dao.DataBaseTableDao;
import com.example.autocodetemplate.service.DataBaseTableService;
import com.example.autocodetemplate.sharding.Enum.EnumPoolManageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Service(value = "dataBaseTableService")
public class DataBaseTableServiceImpl implements DataBaseTableService {
    private final Logger logger = LoggerFactory.getLogger(DataBaseTableServiceImpl.class);

    @Resource
    private DataBaseTableDao dataBaseTableDao;

    @Override
    public int createTableStruByCopy(String createTableName, String baseTableName) {

        int creNum = 0;

        int mum = dataBaseTableDao.isExistTable(EnumPoolManageType.YATES_TEST_DB_1.getValue(), createTableName);
        if (mum > 0) {
            logger.info("[{}]数据库已存在表[{}]", EnumPoolManageType.YATES_TEST_DB_1.getValue(), createTableName);

            return 0;
        }

        try {
             creNum = dataBaseTableDao.createTableStruByCopy(createTableName, baseTableName);
        } catch (Exception e) {
            logger.error("根据模板表创建表失败：{}", e.getMessage());
        }

        return creNum;
    }

}
