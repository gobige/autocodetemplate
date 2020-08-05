package com.example.autocodetemplate.domain.shardjdbc;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TOrder {
    private Integer userId;
    private Integer orderStatus;
    private Integer isDelete;
    private BigDecimal orderFee;
    private Long orderId;
    private Date createTime;

}
