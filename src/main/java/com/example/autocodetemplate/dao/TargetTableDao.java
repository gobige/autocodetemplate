package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.TableStructure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Mapper
public interface TargetTableDao {

    /**
     * 获取表结构
     *
     * @param tableName
     * @return
     */
    Collection<TableStructure> descTableStru(@Param("tableName") String tableName);

    /**
     * 使用注解映射sql语句
     * @param tableName
     * @return
     */
    @Select("desc #{tableName}")
    Collection<TableStructure> descTableStruByAno(@Param("tableName") String tableName);

}
