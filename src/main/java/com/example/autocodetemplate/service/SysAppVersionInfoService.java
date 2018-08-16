package com.example.autocodetemplate.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.example.autocodetemplate.domain.SysAppVersionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAppVersionInfoService {
    SysAppVersionInfo findById(Integer id);

    PageList<SysAppVersionInfo> list(SysAppVersionInfo info);

    void addSysAppVersionInfo(SysAppVersionInfo item);

    void addSysAppVersionInfos(List<SysAppVersionInfo> items);

    void modifySysAppVersionInfo(SysAppVersionInfo item);

    void modifySysAppVersionInfo(List<SysAppVersionInfo> items);

    void deleteById(Integer id);

}