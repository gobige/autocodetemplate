package com.example.autocodetemplate.ohter.practice;

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
public class Apple implements Serializable {

    private static final long serialVersionUID = 7208877163749273274L;

    Apple() {
        super();
    }

    public Apple(Integer seqNo, Integer wight, String country) {
        this.seqNo = seqNo;
        this.wight = wight;
        this.country = country;
    }

    private Integer seqNo;
    private Integer wight;
    private String country;
    private String color;
    private String name;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getWight() {
        return wight;
    }

    public void setWight(Integer wight) {
        this.wight = wight;
    }
}
