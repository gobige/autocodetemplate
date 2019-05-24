package com.example.autocodetemplate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Actor implements Serializable {
    private static final long serialVersionUID = 5221329282798030651L;

    private Date createTime;
    private Integer isDelete;
    private String name;
    private Date updateTime;
    private Integer id;
    private Integer userType;
    private Integer userId;
}