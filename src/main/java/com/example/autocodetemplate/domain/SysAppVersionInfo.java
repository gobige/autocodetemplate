package com.example.autocodetemplate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class SysAppVersionInfo implements Serializable {
    private static final long serialVersionUID = 5221329282798030651L;

    private Integer appVersion;
    private Integer isDelete;
    private String downloadUrl;
    private String remark;
    private String testResultRemark;
    private Date updateTime;
    private String version;
    private Integer platform;
    private Integer isRelease;
    private Date createTime;
    private Integer fileSize;
    private Integer appType;
    private Integer id;
    private Integer isForceUpdate;

}