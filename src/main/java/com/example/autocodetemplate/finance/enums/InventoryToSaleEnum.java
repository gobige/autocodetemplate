package com.example.autocodetemplate.finance.enums;

/**
 * 库存销售指标
 */
public enum InventoryToSaleEnum {
    EarlyRecovery(1, "复苏前期", "inventory-；sales+；去库存-"),
    LATE_RECOVERY(2, "复苏后期", "inventory++；sales+；库存+"),
    PRE_RECESSION(3, "衰退前期", "inventory++；sales-；去库存+"),
    LATE_RECESSION(4, "衰退后期", "inventory--；sales-；去库存-");

    private Integer key;
    private String value;
    private String desc;

    InventoryToSaleEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.desc = description;
    }

    public static InventoryToSaleEnum findByKey(Integer key) {
        for (InventoryToSaleEnum typeEnum : InventoryToSaleEnum.values()) {
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
