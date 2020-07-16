package com.example.autocodetemplate.domain;

import java.io.Serializable;

public
class UserCreateMessage implements Serializable {
    private static final long serialVersionUID = -8995790645422688337L;

    public UserCreateMessage(){}

    public UserCreateMessage(Integer userId, Integer userType) {
        this.userId = userId;
        this.userType = userType;
    }

    private Integer userId;
    private Integer userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
