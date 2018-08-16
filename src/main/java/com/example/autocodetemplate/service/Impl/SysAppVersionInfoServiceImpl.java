package com.example.autocodetemplate.service.Impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.example.autocodetemplate.dao.SysAppVersionInfoDao;
import com.example.autocodetemplate.domain.SysAppVersionInfo;
import com.example.autocodetemplate.service.SysAppVersionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service("sysAppVersionInfoService")
public class SysAppVersionInfoServiceImpl implements SysAppVersionInfoService {
    @Resource
    private SysAppVersionInfoDao sysAppVersionInfoDao;

    @Override
    public SysAppVersionInfo findById(Integer id) {
        return sysAppVersionInfoDao.queryById(id);
    }

    @Override
    public PageList<SysAppVersionInfo> list(SysAppVersionInfo info) {
        PageList pageList = new PageList();
        PageBounds pageBounds = new PageBounds(1, 10);
        pageList = sysAppVersionInfoDao.list(info, pageBounds);

        return pageList;
    }

    @Override
    public void addSysAppVersionInfo(SysAppVersionInfo item) {

    }

    @Override
    public void addSysAppVersionInfos(List<SysAppVersionInfo> items) {

    }

    @Override
    public void modifySysAppVersionInfo(SysAppVersionInfo item) {

    }

    @Override
    public void modifySysAppVersionInfo(List<SysAppVersionInfo> items) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
