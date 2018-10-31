package com.example.autocodetemplate.util;

import com.example.autocodetemplate.controller.GenerateTemplateController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class StringUtil {
    private static final Logger logger = LoggerFactory.getLogger(GenerateTemplateController.class);

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 从标准domain类中获取set语句(用于do，vo，bo等相互转换时赋值场景)
     * @param source 文件来源
     * @param getPrefix get语句前缀（对象句柄名）
     * @param setPrefix set语句前缀（对象句柄名）
     */
    public static String acquireSet(String source, String getPrefix, String setPrefix) {
        int indexBegin = 0;
        int subBegin = 0;
        int subEnd = 0;
        int num = 0;
        StringBuilder setCodes = new StringBuilder("");
        while (true) {
            subBegin = source.indexOf("private", indexBegin);
            if (subBegin == -1) {
                break;
            }
            subEnd = source.indexOf(";", subBegin);
            indexBegin = subEnd;
            num++;
            String lines = source.substring(subBegin, subEnd);
            String[] lineArr = lines.split(" ");
            if (lineArr.length != 3) {
                if (lines.indexOf("=") < 0) {
                    continue;
                } else {
                    String subStr = lines.substring(0,lines.indexOf("="));
                    lineArr = subStr.split(" ");
                }

            }
            char[] paramNameChar = lineArr[2].substring(0,lineArr[2].length()).toCharArray();
            if (123 > paramNameChar[0] && paramNameChar[0] > 96) {
                paramNameChar[0] = (char) (paramNameChar[0] - 32);
            }

            // 首字母大写
            String titleCaseParamName = String.valueOf(paramNameChar);

            setCodes.append(setPrefix +"set" + titleCaseParamName +"(" + getPrefix + "get" + titleCaseParamName + "());\n");
        }
        System.out.println("atuo generate getset" + num);

        return setCodes.toString();
    }


    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 字符串占位符批量填充
     *
     * @param fillStr
     * @param dimensionArr
     * @param splitStr
     * @return
     */
    public static String patchPlaceholderFill(String fillStr, String[][] dimensionArr, String splitStr, String placeholder) {

        StringBuilder stringBuilder = new StringBuilder();
        for (String[] paramArr : dimensionArr) {
            stringBuilder.append(placeholderFill(fillStr, paramArr, placeholder)).append(splitStr);
        }

        return stringBuilder.toString();
    }

    /**
     * 字符串占位符填充
     *
     * @param fillStr
     * @param paramArr
     * @param placeholder
     * @return
     */
    public static String placeholderFill(String fillStr, String[] paramArr, String placeholder) {
        StringBuilder preStr = new StringBuilder();
        preStr.append(" ").append(fillStr).append(" ");
        String[] preStrArr = preStr.toString().split(placeholder);
        if (preStrArr.length - 1 != paramArr.length) {
            logger.info("占位符和参数个数不一致");
            return "";
        }
        for (int i = 0; i < paramArr.length - 1; i++) {
            preStrArr[i] = preStrArr[i] + paramArr[i];
        }

        StringBuilder sqlStr = new StringBuilder();
        for (String str : preStrArr) {
            sqlStr.append(str);
        }

        return sqlStr.toString();
    }


    /**
     * 参数分离
     *
     * @param replaceParam
     * @return
     */
    public static String[] replaceParamStrToArray(String replaceParam, String split) {
        String[] parmAndTypes = replaceParam.split(split);
        if (parmAndTypes.length == 0) {
            logger.info("该数据结构无参数可分离");
            return null;
        }

        for (String param : parmAndTypes) {
            param = param.trim();
        }

        return parmAndTypes;
    }

    /**
     * sql参数特殊处理
     *
     * @param paramArr
     * @return
     */
    public static String[] sqlParamSplit(String[] paramArr) {
        String[] params = new String[paramArr.length];

        String type;
        String param;
        for (int i = 0; i < paramArr.length; i++) {
            int leftBracketIndex = paramArr[i].indexOf("(");
            int rightBracketIndex = paramArr[i].indexOf(")");
            type = paramArr[i].substring(leftBracketIndex + 1, rightBracketIndex);
            type = type.trim();
            param = paramArr[i].substring(0, leftBracketIndex);
            param = param.trim();
            if ("String".equals(type)) {
                params[i] = "'" + param + "'";
            } else if ("Timestamp".equals(type)) {
                params[i] = "'" + param + "'";
            } else {
                params[i] = param;
            }
        }

        return params;
    }

    /**
     * 占位符的sql->可执行sql
     *
     * @param replaceParamStr 参数字符串
     * @param splitSymbol     参数分隔符
     * @param preSql          预编译sql
     * @param placeholder     占位符
     * @return 可执行sql
     */
    public static String autoFillSql(String replaceParamStr, String splitSymbol, String preSql, String placeholder) {
        String[] params = replaceParamStrToArray(replaceParamStr, splitSymbol);
        params = sqlParamSplit(params);
        String excuSql = placeholderFill(preSql, params, placeholder);

        return excuSql;
    }

    /**
     * 格式化mysql 为单行
     * @return
     */
    public static void formatSqlToSingleLine(String buildSqlStr) {
        // 去除换行符
        buildSqlStr = buildSqlStr.replaceAll("\n", " ");
        buildSqlStr = buildSqlStr.replaceAll("\t","");
        System.out.println(buildSqlStr);
    }

    /**
     * 自动生成get，set赋值语句
     *
     * @return
     */
    public static void autoGenerateGetSetByVariable(String getObjClassNamesource, String setObjClassNametarget, String sourcePath) {
        String fileContent = "";
        try {
            fileContent = FileUtil.fileInputStreamToString(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileContent = fileContent.replaceAll("\\s", " ");

        char[] paramNameChar = setObjClassNametarget.substring(0, setObjClassNametarget.length()).toCharArray();

        // 首字母转小写
        if (91 > paramNameChar[0] && paramNameChar[0] > 64) {
            paramNameChar[0] = (char) (paramNameChar[0] + 32);
        }
        String setObjName = String.valueOf(paramNameChar);

        paramNameChar = getObjClassNamesource.substring(0, getObjClassNamesource.length()).toCharArray();
        if (91 > paramNameChar[0] && paramNameChar[0] > 64) {
            paramNameChar[0] = (char) (paramNameChar[0] + 32);
        }
        String getObjName = String.valueOf(paramNameChar);


        System.out.println("public static " + setObjClassNametarget + " " + getObjName + "To" + setObjClassNametarget + "(" + getObjClassNamesource + " " + getObjName + ") {");
        System.out.println(setObjClassNametarget + " " + setObjName + " = " + "new " + setObjClassNametarget + "();");
        System.out.println(StringUtil.acquireSet(fileContent, getObjName + ".", setObjName + "."));
        System.out.println("return " + setObjName + ";");
        System.out.println("}");
    }

    public static void main(String[] args) {

//        autoFillSql();
        formatSqlToSingleLine("SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\t*\n" +
                "\t\tFROM\n" +
                "\t\t\t`wst_order_delivers` a\n" +
                "\t\tWHERE\n" +
                "\t\t\t(a.orderId) IN (\n" +
                "\t\t\t\tSELECT\n" +
                "\t\t\t\t\torderId\n" +
                "\t\t\t\tFROM\n" +
                "\t\t\t\t\t`wst_order_delivers`\n" +
                "\t\t\t\tGROUP BY\n" +
                "\t\t\t\t\torderId\n" +
                "\t\t\t\tHAVING\n" +
                "\t\t\t\t\tCOUNT(*) > 1\n" +
                "\t\t\t)\n" +
                "\t\tORDER BY\n" +
                "\t\t\torderId ASC,\n" +
                "\t\t\tdeliverId DESC\n" +
                "\t) temp");
//        autoGenerateGetSetByVariable("OssApplication","OssApplicationVO","c:/暂存/getset.txt");
    }
}
