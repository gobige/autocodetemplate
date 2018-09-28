package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.Enum.EnumTableColumnTypeRalationJavaType;
import com.example.autocodetemplate.dao.TargetTableDao;
import com.example.autocodetemplate.domain.TableStructure;
import com.example.autocodetemplate.service.GenerateTemplateService;
import com.example.autocodetemplate.util.FileUtil;
import com.example.autocodetemplate.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Service(value = "generateTemplateService")
public class GenerateTemplateServiceImpl implements GenerateTemplateService {
    private Logger logger = LoggerFactory.getLogger(GenerateTemplateServiceImpl.class);

    private final String templatePath = "src\\main\\java\\template\\";
    @Autowired
    private TargetTableDao targetTableDao;

    @Override
    public Boolean generateTemplateByTable(String tableName, Collection<TableStructure> tableStructures) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        if (CollectionUtils.isEmpty(tableStructures)) {
            tableStructures = targetTableDao.descTableStru(tableName);
        }

        // 构造domain
        generateDomain(tableName, tableStructures);
        // 构造mapper
        generateMapper(tableName, tableStructures);

        // 构造dao
        generateDao(tableName);
        // 构造service
        generateService(tableName);

        return true;
    }

    private String generateMapperContent(String tableName, Collection<TableStructure> tableStructures, String domainPath) {
        StringBuilder sb = new StringBuilder();
        String className = StringUtil.lineToHump(tableName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        /**
         * xml schema
         */
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n")
                .append("<mapper namespace=\"").append("template.dao.").append(className).append("Dao").append("\">\n");

        /**
         * resultMap构造
         */
        sb.append("    <resultMap id=\"baseMap\" type=\"").append("template.domain.").append(className).append("\">\n")
                .append("        <id column=\"id\" property=\"id\"/>\n");
        for (TableStructure tableRecord : tableStructures) {
            String jdbcName = tableRecord.getField();
            String javaName = StringUtil.lineToHump(jdbcName);
            sb.append("        <result column=\"").append(jdbcName).append("\" property=\"").append(javaName).append("\"/>\n");
        }
        sb.append("    </resultMap>\n\n");

        sb.append(generateQueryById(tableName));
        sb.append(generateInsertSql(className, tableName, domainPath, tableStructures));
        sb.append(generateInsertsSql(className, tableName, domainPath, tableStructures));
        sb.append(generateUpdateSql(className, tableName, tableStructures));
        sb.append(generateDeleteById(tableName));
        sb.append("</mapper>");

        return sb.toString();
    }

    /**
     * 根据主键id逻辑删除
     *
     * @return
     */
    private String generateDeleteById(String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <update id=\"deleteById\">\n")
                .append("        update ").append(tableName).append("\n").append("        SET is_delete = 1\n")
                .append("        WHERE id = #{id}\n")
                .append("    </update>\n\n");

        return sb.toString();
    }


    /**
     * 构造批量updatesql
     *
     * @param domainName
     * @param tableName
     * @param tableStructures
     * @return
     */
    private String generateUpdatesSql(String domainName, String tableName, Collection<TableStructure> tableStructures) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <update id=\"update").append(domainName).append("\">\n")
                .append("        UPDATE ").append(tableName).append("\n")
                .append("        <set>\n");

        // java对象
        StringBuilder updateStrBuilder = new StringBuilder();
        for (TableStructure tableRecord : tableStructures) {
            String paramName = StringUtil.lineToHump(tableRecord.getField());
            if ("createTime".equals(paramName)) {

            } else if ("updateTime".equals(paramName)) {
                updateStrBuilder.append("update_time = now()\n");
            } else if ("isDelete".equals(paramName)) {

            } else {
                updateStrBuilder.append("            <if test=\"item.").append(paramName).append(" != null and item.").append(paramName).append(" != ''\">\n")
                        .append("                ").append(tableRecord.getField()).append(" = #{item.").append(paramName).append("},\n")
                        .append("            </if>\n");
            }
        }
        sb.append(updateStrBuilder.toString());
        sb.append("        </set>\n")
                .append("        WHERE id = #{item.id}\n")
                .append("    </update>\n\n");

        return sb.toString();
    }

    /**
     * 构造updatesql
     *
     * @param domainName
     * @param tableName
     * @param tableStructures
     * @return
     */
    private String generateUpdateSql(String domainName, String tableName, Collection<TableStructure> tableStructures) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <update id=\"update").append(domainName).append("\">\n")
                .append("        UPDATE ").append(tableName).append("\n")
                .append("        <set>\n");

        // java对象
        StringBuilder updateStrBuilder = new StringBuilder();
        for (TableStructure tableRecord : tableStructures) {
            String paramName = StringUtil.lineToHump(tableRecord.getField());
            if ("createTime".equals(paramName)) {

            } else if ("updateTime".equals(paramName)) {
                updateStrBuilder.append("            update_time = now()\n");
            } else if ("isDelete".equals(paramName)) {

            } else {
                updateStrBuilder.append("            <if test=\"item.").append(paramName).append(" != null and item.").append(paramName).append(" != ''\">\n")
                        .append("                ").append(tableRecord.getField()).append(" = #{item.").append(paramName).append("},\n")
                        .append("            </if>\n");
            }
        }
        sb.append(updateStrBuilder.toString());
        sb.append("        </set>\n")
                .append("        WHERE id = #{item.id}\n")
                .append("    </update>\n\n");

        return sb.toString();
    }

    /**
     * 根据主键id查询
     *
     * @return
     */
    private String generateQueryById(String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <select id=\"queryById\" resultMap=\"baseMap\">\n")
                .append("        SELECT * FROM ").append(tableName).append("\n")
                .append("        WHERE id = #{id}\n")
                .append("    </select>\n\n");

        return sb.toString();
    }

    /**
     * inser多条
     *
     * @param domainName
     * @param tableName
     * @param domainPath
     * @param tableStructures
     * @return
     */
    private String generateInsertsSql(String domainName, String tableName, String domainPath, Collection<TableStructure> tableStructures) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <insert id=\"insert").append(domainName).append("s").append("\" parameterType=\"").append("template.domain.").append(domainName).append("\" flushCache=\"true\">\n")
                .append("        insert into ").append(tableName).append(" (\n");

        // 表字段
        StringBuilder insertFieldStrBuilder = new StringBuilder();
        for (TableStructure tableRecord : tableStructures) {
            insertFieldStrBuilder.append("            ").append(tableRecord.getField()).append(",\n");
        }
        String insertFieldStr = insertFieldStrBuilder.toString();
        char[] insertFieldChar = insertFieldStr.toCharArray();
        insertFieldChar[insertFieldChar.length - 2] = ')';
        insertFieldStr = String.valueOf(insertFieldChar);

        // java对象
        StringBuilder insertParamStrBuilder = new StringBuilder();
        insertParamStrBuilder.append("        <foreach collection=\"items\" item=\"item\" index=\"index\" separator=\",\" >\n            (\n");
        for (TableStructure tableRecord : tableStructures) {
            String paramName = StringUtil.lineToHump(tableRecord.getField());
            if ("createTime".equals(paramName)) {
                insertParamStrBuilder.append("            NOW(),\n");
            } else if ("updateTime".equals(paramName)) {
                insertParamStrBuilder.append("            NOW(),\n");
            } else if ("isDelete".equals(paramName)) {
                insertParamStrBuilder.append("            0,\n");
            } else {
                insertParamStrBuilder.append("            #{item.").append(paramName).append("},\n");
            }
        }
        String insertParamStr = insertParamStrBuilder.toString();
        char[] insertParamChar = insertParamStr.toCharArray();
        insertParamChar[insertParamChar.length - 2] = ')';
        insertParamStr = String.valueOf(insertParamChar);

        sb.append(insertFieldStr).append("        values \n").append(insertParamStr).append("        </foreach>").append("\n").append("    </insert>\n\n");

        return sb.toString();
    }


    /**
     * inser单条
     *
     * @param domainName
     * @param tableName
     * @param domainPath
     * @param tableStructures
     * @return
     */
    private String generateInsertSql(String domainName, String tableName, String domainPath, Collection<TableStructure> tableStructures) {
        StringBuilder sb = new StringBuilder();
        sb.append("    <insert id=\"insert").append(domainName).append("\" parameterType=\"").append("template.domain.").append(domainName).append("\" flushCache=\"true\" keyColumn=\"id\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n")
                .append("        insert into ").append(tableName).append(" (\n");

        // 表字段
        StringBuilder insertFieldStrBuilder = new StringBuilder();
        for (TableStructure tableRecord : tableStructures) {
            insertFieldStrBuilder.append("            ").append(tableRecord.getField()).append(",\n");
        }
        String insertFieldStr = insertFieldStrBuilder.toString();

        char[] insertFieldChar = insertFieldStr.toCharArray();
        insertFieldChar[insertFieldChar.length - 2] = ')';
        insertFieldStr = String.valueOf(insertFieldChar);

        // java对象
        StringBuilder insertParamStrBuilder = new StringBuilder();
        for (TableStructure tableRecord : tableStructures) {
            String paramName = StringUtil.lineToHump(tableRecord.getField());
            if ("createTime".equals(paramName)) {
                insertParamStrBuilder.append("            NOW(),\n");
            } else if ("updateTime".equals(paramName)) {
                insertParamStrBuilder.append("            NOW(),\n");
            } else if ("isDelete".equals(paramName)) {
                insertParamStrBuilder.append("            0,\n");
            } else {
                insertParamStrBuilder.append("            #{item.").append(paramName).append("},\n");
            }
        }
        String insertParamStr = insertParamStrBuilder.toString();

        char[] insertParamChar = insertParamStr.toCharArray();
        insertParamChar[insertParamChar.length - 2] = ')';
        insertParamStr = String.valueOf(insertParamChar);
        sb.append(insertFieldStr).append("        values (\n").append(insertParamStr).append("    </insert>\n\n");

        return sb.toString();
    }

    @Override
    public Boolean generateMapper(String tableName, Collection<TableStructure> tableStructures) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        if (CollectionUtils.isEmpty(tableStructures)) {
            tableStructures = targetTableDao.descTableStru(tableName);
        }

        String content = generateMapperContent(tableName, tableStructures, "template.mapper");

        // 写入文件
        String className = StringUtil.lineToHump(tableName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        File file = new File(templatePath + "\\mapper\\" + className + "Mapper.xml");

        FileUtil.outputFile(content, file);

        return true;
    }


    /**
     * 生成service内容
     *
     * @param serviceName
     * @param currentPath
     * @return
     */
    private String generateService(String serviceName, String currentPath) {
        StringBuilder sb = new StringBuilder();

        // 构造类名 及 序列化代码工作
        String className = StringUtil.lineToHump(serviceName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        sb.append("package ").append(currentPath).append(";\n\n")
                .append("import java.util.List;\n")
                .append("import template.domain.").append(className).append(";\n\n");

        sb.append("public interface ").append(className).append("Service {\n")
                .append("    ").append(className).append(" findById(Integer id);\n\n")
                .append("    void add").append(className).append("(").append(className).append(" item);\n\n")
                .append("    void add").append(className).append("s(").append("List<").append(className).append(">").append(" items);\n\n")
                .append("    void modify").append(className).append("(").append(className).append(" item);\n\n")
                .append("    void modify").append(className).append("(").append("List<").append(className).append(">").append(" items);\n\n")
                .append("    void deleteById(Integer id);\n\n");

        sb.append("}");

        return sb.toString();
    }

    @Override
    public Boolean generateService(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        String content = generateService(tableName, "template.service");

        // 写入文件
        String className = StringUtil.lineToHump(tableName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        File file = new File(templatePath + "\\service\\" + className + "Service.java");

        FileUtil.outputFile(content, file);

        return true;
    }

    /**
     * 生成dao内容
     *
     * @param daoName
     * @param currentPath
     * @return
     */
    private String generateDao(String daoName, String currentPath) {
        StringBuilder sb = new StringBuilder();
        // 构造类名 及 序列化代码工作
        String className = StringUtil.lineToHump(daoName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        sb.append("package ").append(currentPath).append(";\n\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;\n")
                .append("import org.apache.ibatis.annotations.Param;\n")
                .append("import java.util.List;\n")
                .append("import template.domain.").append(className).append(";\n\n");

        sb.append("@Mapper").append("\n");

        sb.append("public interface ").append(className).append("Dao {\n")
                .append("    ").append(className).append(" queryById(@Param(\"id\") Integer id);\n\n")
                .append("    void insert").append(className).append("(@Param(\"item\") ").append(className).append(" item);\n\n")
                .append("    void insert").append(className).append("s").append("(@Param(\"items\") ").append("List<").append(className).append(">").append(" items);\n\n")
                .append("    void update").append(className).append("(@Param(\"item\") ").append(className).append(" item);\n\n")
                .append("    void update").append(className).append("s").append("(@Param(\"items\") ").append("List<").append(className).append(">").append(" items);\n\n")
                .append("    void deleteById(@Param(\"id\") Integer id);\n\n");

        sb.append("}");

        return sb.toString();
    }

    @Override
    public Boolean generateDao(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }

        String content = generateDao(tableName, "template.dao");

        // 写入文件
        String className = StringUtil.lineToHump(tableName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        File file = new File(templatePath + "\\dao\\" + className + "Dao.java");

        FileUtil.outputFile(content, file);

        return true;
    }

    @Override
    public Boolean generateDomain(String tableName, Collection<TableStructure> tableStructures) {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        if (CollectionUtils.isEmpty(tableStructures)) {
            tableStructures = targetTableDao.descTableStru(tableName);
        }

        String content = generateDomain(tableStructures, tableName, "template.domain");

        // 写入文件
        String className = StringUtil.lineToHump(tableName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        File file = new File(templatePath + "\\domain\\" + className + ".java");

        FileUtil.outputFile(content, file);

        return true;
    }

    private String generateDomain(Collection<TableStructure> tableStructures, String domainName, String currentPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(currentPath).append(";\n\n")
                .append("import java.io.Serializable;\n")
                .append("import java.util.Date;\n\n");

        // 构造类名 及 序列化代码工作
        String className = StringUtil.lineToHump(domainName);
        char[] classNameChar = className.toCharArray();
        classNameChar[0] = (char) (classNameChar[0] - 32);
        className = String.valueOf(classNameChar);

        sb.append("public class ").append(className).append(" implements Serializable {\n")
                .append("    private static final long serialVersionUID = 5221329282798030651L;\n\n");


        Map<String, String> tableColTypes = generateDomainParamMap(tableStructures);

        String paramStr = generateDomainParam(tableColTypes);
        sb.append(paramStr).append("\n");

        // 生成getSet
        if (tableColTypes.size() != 0) {
            for (Map.Entry entry : tableColTypes.entrySet()) {
                String getSetStr = generateGetAndSet((String) entry.getKey(), (String) entry.getValue());
                if (!StringUtils.isEmpty(getSetStr)) {
                    sb.append(getSetStr);
                }
            }
        }

        // 生成tostring
        sb.append(generateToStr());

        sb.append("}");

        return sb.toString();
    }

    /**
     * @return
     */
    private String generateToStr() {

        return "";
    }

    /**
     * 生成get set结构
     *
     * @param paramName
     * @param paramType
     * @return
     */
    private String generateGetAndSet(String paramName, String paramType) {
        if (StringUtils.isEmpty(paramName) || StringUtils.isEmpty(paramType)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        String setStr = generateSet(paramName, paramType);
        String getStr = generateGet(paramName, paramType);
        sb.append(setStr).append(getStr);

        return sb.toString();
    }

    /**
     * 生成set结构
     *
     * @param paramName
     * @param paramType
     * @return
     */
    private String generateSet(String paramName, String paramType) {
        if (StringUtils.isEmpty(paramName) || StringUtils.isEmpty(paramType)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        char[] paramNameChar = paramName.toCharArray();
        paramNameChar[0] = (char) (paramNameChar[0] - 32);

        // 首字母大写
        String titleCaseParamName = String.valueOf(paramNameChar);
        sb.append("    public void").append(" set").append(titleCaseParamName).append("(").append(paramType).append(" ").append(paramName).append(") {\n")
                .append("        this.").append(paramName).append(" = ").append(paramName).append(";\n")
                .append("    }\n");

        return sb.toString();
    }

    /**
     * 生成get结构
     *
     * @param paramName
     * @param paramType
     * @return
     */
    private String generateGet(String paramName, String paramType) {
        if (StringUtils.isEmpty(paramName) || StringUtils.isEmpty(paramType)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        char[] paramNameChar = paramName.toCharArray();
        paramNameChar[0] = (char) (paramNameChar[0] - 32);

        // 首字母大写
        String titleCaseParamName = String.valueOf(paramNameChar);
        sb.append("    public ").append(paramType).append(" get").append(titleCaseParamName).append("() {\n")
                .append("        return ").append(paramName).append(";\n")
                .append("    }\n");

        return sb.toString();
    }

    /**
     * 生成domain中参数String
     *
     * @param
     * @return
     */
    private String generateDomainParam(Map<String, String> tableColTypes) {
        StringBuilder sb = new StringBuilder();

        if (tableColTypes.size() != 0) {
            for (Map.Entry entry : tableColTypes.entrySet()) {
                sb.append("    private ").append(entry.getValue()).append(" ").append(entry.getKey()).append(";\n");
            }
        }

        return sb.toString();
    }

    /**
     * 生成domain参数的名称，类型的map结构
     *
     * @param tableStructures
     * @return
     */
    private Map<String, String> generateDomainParamMap(Collection<TableStructure> tableStructures) {
        Map<String, String> tableColTypes = new HashMap<>();

        for (TableStructure tableRecord : tableStructures) {
            EnumTableColumnTypeRalationJavaType typeRelation = EnumTableColumnTypeRalationJavaType.findByJdbcType(tableRecord.getType());
            if (typeRelation == null) {
                logger.info("无法识别的jdbc类型" + tableRecord.getType());
                continue;
            }

            String paramName = StringUtil.lineToHump(tableRecord.getField());
            if (tableColTypes.containsKey(paramName)) {
                logger.info("domain参数名重复" + tableRecord.getField());
                continue;
            }

            tableColTypes.put(paramName, typeRelation.getJavaType());
        }

        return tableColTypes;
    }

}
