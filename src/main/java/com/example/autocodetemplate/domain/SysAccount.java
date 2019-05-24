package com.example.autocodetemplate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class SysAccount implements Serializable {

    private static final long serialVersionUID = -1391329771056753154L;


    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer userType;
    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private String logingName;

    /**
     *
     */
    private String passWord;
    /**
     *
     */
    private String salt;

}
