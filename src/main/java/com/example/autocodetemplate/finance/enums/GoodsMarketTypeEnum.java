package com.example.autocodetemplate.finance.enums;

public enum GoodsMarketTypeEnum {
    MEDICAL_SERVICE(1, "医疗服务", ""),
    CRO(2, "CRO", ""),
    REAL_ESTATE(3, "房地产", ""),
    CONSUMPTION(4, "食品，消费", "");

    private Integer key;
    private String value;
    private String desc;

    GoodsMarketTypeEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    public static GoodsMarketTypeEnum findByKey(Integer key) {
        for (GoodsMarketTypeEnum typeEnum : GoodsMarketTypeEnum.values()) {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
