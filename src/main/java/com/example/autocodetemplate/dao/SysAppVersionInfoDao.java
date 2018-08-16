package com.example.autocodetemplate.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.autocodetemplate.domain.SysAppVersionInfo;

import java.util.List;

@Mapper
public interface SysAppVersionInfoDao {
    SysAppVersionInfo queryById(@Param("id") Integer id);

    PageList<SysAppVersionInfo> list(@Param("info") SysAppVersionInfo info, PageBounds pageBounds);

    void insertSysAppVersionInfo(@Param("item") SysAppVersionInfo item);

    void insertSysAppVersionInfos(@Param("items") List<SysAppVersionInfo> items);

    void updateSysAppVersionInfo(@Param("item") SysAppVersionInfo item);

    void updateSysAppVersionInfos(@Param("items") List<SysAppVersionInfo> items);

    void deleteById(@Param("id") Integer id);

}