package com.example.autocodetemplate.finance.enums;

/**
 * 经济周期指标
 */
public enum BusinessCycleEnum {
    TROUGH(1, "低谷", "GDP增长率由负变正；high unemployment，increase temporary；房子，消费开始increase；inflation decrease通胀缓和"),
    EXPANSION(2, "经济扩张", "GDP increse;unemployment decrease；invest，home 生产投资，房屋增长；生产带动进口增加；inflation increase；"),
    PEAK(3, "顶峰", "GDP decrease；失业率降低，hiring slow；consumer/invest slow消费，投资增长缓慢；inflation increase"),
    CONTRACTION(4, "紧缩", "GDP 由正转负；工作时长减少，失业率上升；消费投资减少；通胀率降低，滞后；生产导致进口下降");

    private Integer key;
    private String value;
    private String desc;

    BusinessCycleEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    public static BusinessCycleEnum findByKey(Integer key) {
        for (BusinessCycleEnum typeEnum : BusinessCycleEnum.values()) {
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
