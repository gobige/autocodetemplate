package com.example.autocodetemplate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataBaseTableDao {

    /**
     * 根据模板表创建一张新表
     * @param createTableName 新建表名
     * @param baseTableName 模板表名
     * @return
     */
    int createTableStruByCopy(@Param("createTableName") String createTableName, @Param("baseTableName") String baseTableName);

    /**
     * 查询某个数据库某张表是否已经存在
     * @param dbName 数据库名
     * @param tableName 表名
     * @return
     */
    int isExistTable(@Param("dbName") String dbName, @Param("tableName") String tableName);

}