package com.example.autocodetemplate.domain;

import com.alibaba.fastjson.JSONObject;

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
        this.setProvince(jsonResult.getString("province"));
        this.setCity(jsonResult.getString("city"));
        this.setArea(jsonResult.getString("area"));
        this.setPostCode(jsonResult.getString("post_code"));
        this.setAreaCode(jsonResult.getString("area_code"));

        return this;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public String toString() {
        return "AreaAndPostCodeResult{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", postCode='" + postCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
