package com.example.autocodetemplate.filter;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;


public class GenerateTempFilter implements Serializable {

    private static final long serialVersionUID = 5221329282798030651L;

    private String tableName;
    private String dbUrl;
    private String dbUserName;
    private String dbPassword;
    private String jdbcDriver;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
