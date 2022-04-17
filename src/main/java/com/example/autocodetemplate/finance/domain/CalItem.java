package com.example.autocodetemplate.finance.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 计算项
 */
@Getter
@Setter
public class CalItem {

    public Double coeffi;
    public Double val;

    public Double calItem()  {
        return BigDecimal.valueOf(this.coeffi).multiply(BigDecimal.valueOf(this.val))
                .doubleValue();
    }

}
