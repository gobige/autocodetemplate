package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.AreaAndPostCodeResult;
import com.example.autocodetemplate.domain.OcrRecResult;

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
public interface AreaCodeAndPostCodeServcie {

    /**
     *  根据名称查询全国各地区的邮编以及区号 数据来源"第三方"
     * @param name
     * @return
     */
    List<AreaAndPostCodeResult> getAreaCodeAndPostCode(String name) throws Exception;
}
