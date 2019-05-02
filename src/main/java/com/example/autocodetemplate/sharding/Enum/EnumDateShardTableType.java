package com.example.autocodetemplate.sharding.Enum;

/**
 * 按日期拆分表类型
 */
public enum EnumDateShardTableType {
    MONTHLY_TABLE(1,"yyyy_MM","月表"),
    DAILY_TABLE(2,"yyyy_MM_dd","日表");

    EnumDateShardTableType(Integer key, String value, String description) {
        setKey(key);
        setValue(value);
        setDescription(description);

    }

    private Integer key;
    private String value;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
