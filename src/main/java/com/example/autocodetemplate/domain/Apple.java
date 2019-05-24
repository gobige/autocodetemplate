package com.example.autocodetemplate.domain;

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

}
