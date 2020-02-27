package com.example.autocodetemplate.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@ToString
public class SpringTestFilter implements Serializable {

    private static final long serialVersionUID = 5221329282798030651L;

    public SpringTestFilter() {}

    public SpringTestFilter(String testNotNullParam, Integer num) {
        this.setTestNotNullParam(testNotNullParam);
        this.num = num;
    }
    @NotNull
    private String testNotNullParam;
    private Integer num;


}
