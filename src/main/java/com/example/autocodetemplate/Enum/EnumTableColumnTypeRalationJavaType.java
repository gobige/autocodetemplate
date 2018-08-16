package com.example.autocodetemplate.Enum;

import org.springframework.util.StringUtils;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public enum EnumTableColumnTypeRalationJavaType {

    INT("int", "Integer", "整形"),
    TINYINT("tinyint", "Integer", "整形"),
    SMALLINT("smallint", "Integer", "整形"),
    MEDIUMINT("mediumint", "Integer", "整形"),
    INTEGER("integer", "Integer", "整形"),
    BIGINT("bigint", "Integer", "整形"),
    DOUBLE("double", "Double", "双精度浮点数"),
    FLOAT("float", "Double", "单精度浮点数"),
    DECIMAL("decimal", "Double", "科学计算数"),
    CHAR("char", "Character", "字符"),
    DATETIME("datetime", "Date", "时间"),
    TIMESTAMP("timestamp", "Date", "时间戳"),
    VARCHAR("varchar", "String", "字符串");

    private String jdbcType;
    private String javaType;
    private String desc;

    EnumTableColumnTypeRalationJavaType(String jdbcType, String javaType, String desc) {
        setJdbcType(jdbcType);
        setJavaType(javaType);
        setDesc(desc);
    }

    public static EnumTableColumnTypeRalationJavaType findByJdbcType(String jdbcType) {
        if (StringUtils.isEmpty(jdbcType)) {
            return null;
        }
        for (EnumTableColumnTypeRalationJavaType relation : EnumTableColumnTypeRalationJavaType.values()) {
            if (relation.getJdbcType().equals(jdbcType)) {
                return relation;
            }
        }

        return null;
    }

    public static EnumTableColumnTypeRalationJavaType findByJavaType(String javaType) {
        if (StringUtils.isEmpty(javaType)) {
            return null;
        }
        for (EnumTableColumnTypeRalationJavaType relation : EnumTableColumnTypeRalationJavaType.values()) {
            if (relation.getJavaType().equals(javaType)) {
                return relation;
            }
        }

        return null;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
