package com.example.autocodetemplate.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
public class AreaAndPostCodeResult implements Serializable {
    private static final long serialVersionUID = -1778183233488963529L;

    /**
     *  #配置字符串信息
     */
    private String province;
    /**
     *  #输入图片的角度（顺时针旋转），［0， 90， 180，270］
     */
    private String city;
    /**
     * #注册号，没有识别出来时返回"FailInRecognition"
     */
    private String area;
    /**
     * #公司名称，没有识别出来时返回"FailInRecognition"
     */
    private String postCode;
    /**
     *  #公司类型，没有识别出来时返回"FailInRecognition"
     */
    private String areaCode;

    public AreaAndPostCodeResult jsonToOcrRecResult(JSONObject jsonResult) {
        this.province = jsonResult.getString("province");
        this.city = jsonResult.getString("city");
        this.area = jsonResult.getString("area");
        this.postCode = jsonResult.getString("post_code");
        this.areaCode = jsonResult.getString("area_code");

        return this;
    }

}
