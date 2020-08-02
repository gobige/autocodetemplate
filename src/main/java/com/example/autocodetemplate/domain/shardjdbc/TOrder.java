package com.example.autocodetemplate.domain.shardjdbc;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TOrder {
    private Integer userId;
    private Long orderId;
    private Date createTime;


}
