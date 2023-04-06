package com.example.autocodetemplate.finance.enums;

/**
 * 趋势枚举
 */
public enum TrendEnum {
    UP(1, "上升"),
    DOWN(-1, "下降"),
    STABLE(0, "持平");

    private Integer key;
    private String value;

    TrendEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static TrendEnum findByKey(Integer key) {
        for (TrendEnum typeEnum : TrendEnum.values()) {
            if (typeEnum.getKey().equals(key)) {
                return typeEnum;
            }
        }

        return null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
