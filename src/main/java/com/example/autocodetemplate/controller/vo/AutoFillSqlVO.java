package com.example.autocodetemplate.controller.vo;

import java.io.Serializable;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class AutoFillSqlVO implements Serializable {

    private static final long serialVersionUID = -296090589085160920L;
    /**
     *  参数字符串
     */
    private String replaceParamStr;
    /**
     * 参数分隔符
     */
    private String splitSymbol;
    /**
     * 预编译sql
     */
    private String preSql;
    /**
     * 占位符
     */
    private String placeholder;

    public String getReplaceParamStr() {
        return replaceParamStr;
    }

    public void setReplaceParamStr(String replaceParamStr) {
        this.replaceParamStr = replaceParamStr;
    }

    public String getSplitSymbol() {
        return splitSymbol;
    }

    public void setSplitSymbol(String splitSymbol) {
        this.splitSymbol = splitSymbol;
    }

    public String getPreSql() {
        return preSql;
    }

    public void setPreSql(String preSql) {
        this.preSql = preSql;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
