package com.example.autocodetemplate.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
@ToString
public class ValidAnoTestFilter implements Serializable {

    private static final long serialVersionUID = 5221329282798030651L;

    @NotNull(message = "不能为空字段")
    @DecimalMin(value = "1.1",message = "最小不能小于1.1")
    @DecimalMax(value = "10.1",message = "最大不能超过10.1")
    private Double doubleValue;

    @NotNull(message = "不能为空字段")
    @Min(value = 2,message = "最小不能小于2")
    @Max(value = 20,message = "最大不能超过20")
    private Integer intValue;

    @AssertFalse(message = "必须为false字段")
    private Boolean AssertFalseValue;

    @AssertTrue(message = "必须为true字段")
    private Boolean AssertTrueValue;

    @FutureOrPresent(message = "需要一个将来或现在日期") // 可为null
//    @Future(message = "需要一个将来日期") // 可为null
    // @Past //只能去过去的日期
    private Date date;

//    @Negative(message = "数字，负数")
    @NegativeOrZero(message = "数字，负数或0")
    private Integer negativeNum;
//    @Positive(message = "数字，正数")
    @PositiveOrZero(message = "数字，正数或0")
    private Integer positiveNum;

    @NotBlank(message = "不能为空或空字符")
    private String notBlank;

    @NotEmpty(message = "不能为空集合，字符串，数组")
    private Collection<Integer> notEmpty;

//    @Pattern(regexp = "1,2,3", message = "正则表达式不对")
//    private String pattern;

    @Email(message = "必须为email格式")
    private String email;

    @Size(min = 1, max = 5, message = "元素数量最小1，最大5")
    private Collection<Integer> size;

    public Collection<Integer> getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(Collection<Integer> notEmpty) {
        this.notEmpty = notEmpty;
    }

    public Collection<Integer> getSize() {
        return size;
    }

    public void setSize(Collection<Integer> size) {
        this.size = size;
    }
}
