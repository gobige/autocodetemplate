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
public class TableStructure implements Serializable {

    private static final long serialVersionUID = 5221329282798030651L;

    private String field;
    private String type;

}
