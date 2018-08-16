package com.example.autocodetemplate.Enum;

/**
 * <p></p>
 * <p>Project: </p>
 * <p>ModuleID: thirdParty---juhe</p>
 * <p>Comments: 可查询彩票类型</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public enum EnumLotteryQueryType {
    SSQ("ssq","双色球","双色球"),
    DLT("dlt","超级大乐透","超级大乐透"),
    Ssq("fcsd","福彩3D","福彩3D"),
    QXC("qxc","七星彩","七星彩"),
    PLS("pls","排列三","排列三"),
    PLW("plw","排列五","排列五");


    private EnumLotteryQueryType(String key, String value, String description) {
        setKey(key);
        setValue(value);
        setDescription(description);
    }
    private String key;
    private String value;
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
