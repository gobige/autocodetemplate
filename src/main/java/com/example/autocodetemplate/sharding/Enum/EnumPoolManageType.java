package com.example.autocodetemplate.sharding.Enum;

/**
 * 数据库名称
 */
public enum EnumPoolManageType {
    WST_CARHOUSE(1,"wst_carhouse","wst_carhouse数据库"),
    WST_FINANCE(2,"wst_finance","wst_finance数据库"),
    YATES_TEST_DB_1(3,"testdb","杨超测试数据库"),
    YATES_TEST_DB_2(4,"testdb2","杨超测试数据库2");

    private Integer key;
    private String value;
    private String description;

    EnumPoolManageType(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
