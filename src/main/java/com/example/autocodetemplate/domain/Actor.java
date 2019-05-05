package com.example.autocodetemplate.domain;

import java.io.Serializable;
import java.util.Date;

public class Actor implements Serializable {
    private static final long serialVersionUID = 5221329282798030651L;

    private Date createTime;
    private Integer isDelete;
    private String name;
    private Date updateTime;
    private Integer id;
    private Integer userType;
    private Integer userId;

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public Integer getUserType() {
        return userType;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return userId;
    }
}