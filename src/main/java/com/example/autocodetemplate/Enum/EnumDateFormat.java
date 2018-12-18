package com.example.autocodetemplate.Enum;

public enum EnumDateFormat {

    FORMAT_STYLE_1(1,"yyyy/MM/dd","yyyy/MM/dd"),
    FORMAT_STYLE_2(2,"yyyy-MM-dd","yyyy-MM-dd"),
    FORMAT_STYLE_3(3,"yyyy/MM/dd  HH:mm:ss","yyyy/MM/dd HH:mm:ss"),
    FORMAT_STYLE_4(4,"yyyy-MM-dd  HH:mm:ss","yyyy-MM-dd HH:mm:ss"),
    FORMAT_STYLE_5(5,"yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm");

    EnumDateFormat(int key, String value, String description) {
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
