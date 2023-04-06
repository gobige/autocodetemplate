package com.example.autocodetemplate.finance.observable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EconomicDataDTO {
    public EconomicDataDTO() {}

    public EconomicDataDTO(Integer cycle) {
        this.cycle = cycle;
    }
    private Integer cycle;
}
