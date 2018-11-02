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
public class AutoGenerateGetSetVO implements Serializable {

    private static final long serialVersionUID = 3679875742655366932L;

    /**
     * 获取值class名
     */
    private String getObjClassNamesource;
    /**
     * 赋值class名
     */
    private String setObjClassNametarget;
    /**
     * 赋值class本地文件路由
     */
    private String sourcePath;
    /**
     * 赋值class属性字符串
     */
    private String stringContent;

    public String getGetObjClassNamesource() {
        return getObjClassNamesource;
    }

    public void setGetObjClassNamesource(String getObjClassNamesource) {
        this.getObjClassNamesource = getObjClassNamesource;
    }

    public String getSetObjClassNametarget() {
        return setObjClassNametarget;
    }

    public void setSetObjClassNametarget(String setObjClassNametarget) {
        this.setObjClassNametarget = setObjClassNametarget;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getStringContent() {
        return stringContent;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }
}
