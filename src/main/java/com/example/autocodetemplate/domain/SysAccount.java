package com.example.autocodetemplate.domain;

import java.io.Serializable;


public class SysAccount implements Serializable {

    private static final long serialVersionUID = -1391329771056753154L;


    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer userType;
    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private String logingName;

    /**
     *
     */
    private String passWord;
    /**
     *
     */
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userType=" + userType +
                ", userId=" + userId +
                '}';
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

    public String getLogingName() {
        return logingName;
    }

    public void setLogingName(String logingName) {
        this.logingName = logingName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
