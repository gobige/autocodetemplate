package com.example.autocodetemplate.domain;

import java.io.Serializable;
import java.util.Date;

public class SysAppVersionInfo implements Serializable {
    private static final long serialVersionUID = 5221329282798030651L;

    private Integer appVersion;
    private Integer isDelete;
    private String downloadUrl;
    private String remark;
    private Date updateTime;
    private String version;
    private Integer platform;
    private Integer isRelease;
    private Date createTime;
    private Integer fileSize;
    private Integer appType;
    private Integer id;
    private Integer isForceUpdate;

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    public Integer getIsRelease() {
        return isRelease;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setIsForceUpdate(Integer isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public Integer getIsForceUpdate() {
        return isForceUpdate;
    }
}