package com.example.autocodetemplate.util;

import com.example.autocodetemplate.controller.GenerateTemplateController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public final class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static void main(String[] args) {
                formatSqlToSingleLine("");

//        String singleSqlStr = StringUtil.autoFillSql("1(Integer), 110101000(Integer), 110102000(Integer), 110105000(Integer), 110106000(Integer), 110107000(Integer), 110108000(Integer), 110109000(Integer), 110111000(Integer), 110112000(Integer), 110113000(Integer), 110114000(Integer), 110115000(Integer), 110116000(Integer), 110117000(Integer), 110118000(Integer), 110119000(Integer), 2018-10-01 00:00:00.0(Timestamp), 2018-12-31 00:00:00.0(Timestamp)",
//                ",",
//                "SELECT SUM(today_income_integral) point,user_type,user_id FROM stat_income_integral_userinfo_day WHERE 1= 1 AND user_type = ? AND area_id IN ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) AND stat_date BETWEEN ? AND ? GROUP BY user_type,user_id ORDER BY SUM(today_income_integral) DESC",
//                "\\?");
//        System.out.println(singleSqlStr);


        System.out.println(autoGenerateGetSetByVariable("activityVO", "activityPO", "c:/暂存/getset.txt", null));

//        sqlInStr("c:/暂存/getset.txt");


        char[] chars = "000000000".toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : chars) {
            characters.add(c);
        }
        System.out.println(recursiveGetNextChar(characters));

    }

    /**
     * format string
     */
    public static String formatStr(final String str) {
       String returnStr = String.format("%s%s%s%s", "vvv","123","aaaa","gggg");


        return returnStr;
    }

    /**
     * 按位数对字串左边进行补零
     */
    public static String addLeftZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 从标准domain类中获取set语句(用于do，vo，bo等相互转换时赋值场景)
     *
     * @param source    文件来源
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
                    String subStr = lines.substring(0, lines.indexOf("="));
                    lineArr = subStr.split(" ");
                }

            }
            char[] paramNameChar = lineArr[2].substring(0, lineArr[2].length()).toCharArray();
            if (123 > paramNameChar[0] && paramNameChar[0] > 96) {
                paramNameChar[0] = (char) (paramNameChar[0] - 32);
            }

            // 首字母大写
            String titleCaseParamName = String.valueOf(paramNameChar);

            setCodes.append(setPrefix + "set" + titleCaseParamName + "(" + getPrefix + "get" + titleCaseParamName + "());\n");
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
     *
     * @return
     */
    public static String formatSqlToSingleLine(String buildSqlStr) {
        // 去除换行符
        buildSqlStr = buildSqlStr.replaceAll("\n", " ");
        buildSqlStr = buildSqlStr.replaceAll("\t", "");

        return buildSqlStr;
    }

    /**
     * 自动生成get，set赋值语句
     *
     * @param getObjClassNamesource 源class名
     * @param setObjClassNametarget 目标class名
     * @param sourcePath            本地来源文件地址
     * @param stringContent         现成文件流
     * @return
     */
    public static String autoGenerateGetSetByVariable(String getObjClassNamesource, String setObjClassNametarget, String sourcePath, String stringContent) {
        StringBuilder returnStr = new StringBuilder();
        Optional optional = Optional.ofNullable(stringContent);
        String fileContent = "";
        if (optional.isPresent()) {
            fileContent = (String) optional.get();
        } else {
            try {
                fileContent = FileUtil.fileInputStreamToString(sourcePath);
            } catch (IOException e) {
                System.err.println("IO读取文件流出错" + e.getMessage());
            }
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

        returnStr.append("public static " + setObjClassNametarget + " " + getObjName + "To" + setObjClassNametarget + "(" + getObjClassNamesource + " " + getObjName + ") {\n")
                .append(setObjClassNametarget + " " + setObjName + " = " + "new " + setObjClassNametarget + "();\n")
                .append(StringUtil.acquireSet(fileContent, getObjName + ".", setObjName + "."))
                .append("return " + setObjName + ";\n")
                .append("}");

        return returnStr.toString();
    }



    public static String sqlInStr(String sourcePath) {
        String fileContent = "";
        try {
            fileContent = FileUtil.fileInputStreamToString(sourcePath);
        } catch (IOException e) {
            System.err.println("IO读取文件流出错" + e.getMessage());
        }

        fileContent = fileContent.replaceAll("\\r\\n", ",");
        System.out.println(fileContent);

        return fileContent;
    }

    /**
     * 递归生成reffercode
     * @param characters
     * @return
     */
    private static String recursiveGetNextChar(List<Character> characters) {
        if (characters.size() == 1) {
            return getNextChar(characters.get(characters.size() - 1)) + "";
        }else {
            char pro = getNextChar(characters.get(characters.size() - 1));
            if (pro == '0') {
                return recursiveGetNextChar(characters.subList(0, characters.size() - 1)) + pro + "";
            }else {
                return readCharFromList(characters.subList(0, characters.size() - 1)) + pro + "";
            }
        }
    }

    private static String readCharFromList(List<Character> characters) {
        String str = "";
        for (char c : characters) {
            str += c;
        }

        return str;
    }

    /**
     *
     * @param c
     * @return
     */
    private static char getNextChar(char c) {
        // 0~9
        if (48 <= (int) c && (int) c < 57) {
            return (char)((int)c + 1);
        }else if((int) c == 57){
            return 'A';
            // A~Z
        } else if (65 <= (int) c && (int) c < 90) {
            return (char)((int)c + 1);
        }else if ((int) c == 90){
            return '0';
        }else {
            return '~';
        }
    }

}