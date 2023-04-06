package com.example.autocodetemplate.finance.observable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RateDataDTO {
    public RateDataDTO() {}

    public RateDataDTO(Double rate) {
        this.rate = rate;
    }
    private Double rate;
    /**
     * -1 下降
     * 1 上升
     * 0 不变
     */
    private Integer trend;
}
