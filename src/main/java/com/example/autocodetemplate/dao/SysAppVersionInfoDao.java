package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.provider.SysAppVersionInfoSqlProvider;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.*;
import com.example.autocodetemplate.domain.SysAppVersionInfo;

import java.util.List;

@Mapper
public interface SysAppVersionInfoDao {

    /**
     *
     * @param id
     * @return
     */
    SysAppVersionInfo queryById(@Param("id") Integer id);

    /**
     * @Select
     * @Insert
     * @Update
     * @Delete
     * @Results 用于填写结果集的多个字段的映射关系.
     * @Result 用于填写结果集的单个字段的映射关系.
     * @ResultMap 根据ID关联XML里面.
     * @SelectProvider 其中type表示工具类，method 表示工具类的某个方法，用于返回具体的SQL
     * @InsertProvider
     * @UpdateProvider
     * @DeleteProvider
     *
     *
     * @return
     */

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "testResultRemark",column = "remark")
    })
    @Select("SELECT * FROM sys_app_version_info WHERE id = #{id}")
    SysAppVersionInfo queryByIdByAno(@Param("id") Integer id);

    @SelectProvider(type = SysAppVersionInfoSqlProvider.class, method = "queryById")
    SysAppVersionInfo queryByIdBySelectProvider(@Param("id") Integer id);

}