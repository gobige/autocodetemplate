package com.example.autocodetemplate.finance.enums;

/**
 * 劳动力水平指标
 */
public enum LableLevelEnum {
    EarlyRecovery(1, "复苏前期", "临时工多，不会大量雇佣，失业率高；capital 低"),
    LATE_RECOVERY(2, "复苏后期", "大规模雇佣，失业率低；capital 上升"),
    PRE_RECESSION(3, "衰退前期", "劳动力放假，失业率低；capital 高"),
    LATE_RECESSION(4, "衰退后期", "裁员，失业率高；capital 下降");

    private Integer key;
    private String value;
    private String desc;

    LableLevelEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    public static LableLevelEnum findByKey(Integer key) {
        for (LableLevelEnum typeEnum : LableLevelEnum.values()) {
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
