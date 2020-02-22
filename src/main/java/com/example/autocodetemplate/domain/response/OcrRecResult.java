package com.example.autocodetemplate.domain.response;

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

public class OcrRecResult implements Serializable {
    private static final long serialVersionUID = -1778183233488963529L;

    /**
     *  #配置字符串信息
     */
    private String config_str;
    /**
     *  #输入图片的角度（顺时针旋转），［0， 90， 180，270］
     */
    private String angle;
    /**
     * #注册号，没有识别出来时返回"FailInRecognition"
     */
    private String reg_num;
    /**
     * #公司名称，没有识别出来时返回"FailInRecognition"
     */
    private String name;
    /**
     *  #公司类型，没有识别出来时返回"FailInRecognition"
     */
    private String type;
    /**
     *  #公司法人，没有识别出来时返回"FailInRecognition"
     */
    private String person;
    /**
     * #公司注册日期(例：证件上为"2014年04月16日"，算法返回"20140416")
     */
    private String establish_date;
    /**
     *  #公司营业期限终止日期(例：证件上为"2014年04月16日至2034年04月15日"，算法返回"20340415")
     *     #当前算法将日期格式统一为输出为"年月日"(如"20391130"),并将"长期"表示为"29991231"，若证件上没有营业期限，则默认其为"长期",返回"29991231"。
     */
    private String valid_period;
    /**
     * #公司地址，没有识别出来时返回"FailInRecognition"
     */
    private String address;
    /**
     * #注册资本，没有识别出来时返回"FailInRecognition"
     */
    private String capital;
    /**
     *  #经营范围，没有识别出来时返回"FailInRecognition"
     */
    private String business;
    /**
     * #国徽位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     */
    private String emblem;
    /**
     *  #标题位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     */
    private String title;
    /**
     *  #印章位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     */
    private String stamp;
    /**
     * #二维码位置［top,left,height,width］，没有识别出来时返回"FailInDetection"
     */
    private String qrcode;
    /**
     * #识别成功与否 true/false
     */
    private String success;
    /**
     * request_id
     */
    private String request_id;

    public OcrRecResult jsonToOcrRecResult(JSONObject jsonResult) {
        this.setConfig_str(jsonResult.getString("config_str"));
        this.setType(jsonResult.getString("type"));
        this.setPerson(jsonResult.getString("person"));
        this.setEstablish_date(jsonResult.getString("establish_date"));
        this.setAngle(jsonResult.getString("angle"));
        this.setReg_num(jsonResult.getString("reg_num"));
        this.setName(jsonResult.getString("name"));
        this.setValid_period(jsonResult.getString("valid_period"));
        this.setAddress(jsonResult.getString("address"));
        this.setCapital(jsonResult.getString("capital"));
        this.setBusiness(jsonResult.getString("business"));
        this.setEmblem(jsonResult.getString("emblem"));
        this.setTitle(jsonResult.getString("title"));
        this.setStamp(jsonResult.getString("stamp"));
        this.setQrcode(jsonResult.getString("qrcode"));
        this.setSuccess(jsonResult.getString("success"));
        this.setRequest_id(jsonResult.getString("request_id"));

        return this;
    }

    public String getConfig_str() {
        return config_str;
    }

    public void setConfig_str(String config_str) {
        this.config_str = config_str;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getReg_num() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num = reg_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEstablish_date() {
        return establish_date;
    }

    public void setEstablish_date(String establish_date) {
        this.establish_date = establish_date;
    }

    public String getValid_period() {
        return valid_period;
    }

    public void setValid_period(String valid_period) {
        this.valid_period = valid_period;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "OcrRecResult{" +
                "config_str='" + config_str + '\'' +
                ", angle='" + angle + '\'' +
                ", reg_num='" + reg_num + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", person='" + person + '\'' +
                ", establish_date='" + establish_date + '\'' +
                ", valid_period='" + valid_period + '\'' +
                ", address='" + address + '\'' +
                ", capital='" + capital + '\'' +
                ", business='" + business + '\'' +
                ", emblem='" + emblem + '\'' +
                ", title='" + title + '\'' +
                ", stamp='" + stamp + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", success='" + success + '\'' +
                ", request_id='" + request_id + '\'' +
                '}';
    }
}
