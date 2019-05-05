package com.example.autocodetemplate.filter;

import java.io.Serializable;
import java.util.Date;

public class ActorFilter implements Serializable {
    private static final long serialVersionUID = 5221329282798030651L;

    private Date queryStartCreateTime;
    private Date queryEndCreateTime;
    private String name;
    private Integer id;
    private Integer userType;
    private Integer userId;

    public Date getQueryStartCreateTime() {
        return queryStartCreateTime;
    }

    public void setQueryStartCreateTime(Date queryStartCreateTime) {
        this.queryStartCreateTime = queryStartCreateTime;
    }

    public Date getQueryEndCreateTime() {
        return queryEndCreateTime;
    }

    public void setQueryEndCreateTime(Date queryEndCreateTime) {
        this.queryEndCreateTime = queryEndCreateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}