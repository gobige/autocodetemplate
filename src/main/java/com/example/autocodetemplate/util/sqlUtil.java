package com.example.autocodetemplate.util;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class sqlUtil {

    /**
     * 预编译sql和参数合并为完整sql
     *
     * @param preSql
     * @param replaceParam
     */
    public static void precompileSqlToCompSql(String preSql, String replaceParam) {
        String[] param = replaceParamStrToArray(replaceParam);
        preSql = " " + preSql + " ";
        String[] preSqlArr = preSql.split("\\?");
        if (preSqlArr.length - 1 != param.length) {
            System.out.println("占位符和参数个数不一致");
            return;
        }
        for (int i = 0; i < preSqlArr.length - 1; i++) {
            preSqlArr[i] = preSqlArr[i] + param[i];
        }

        StringBuilder sqlStr = new StringBuilder("");
        for (String str : preSqlArr) {
            sqlStr.append(str);
        }
        System.out.println(sqlStr);

    }

    /**
     * 参数分离
     *
     * @param replaceParam
     * @return
     */
    public static String[] replaceParamStrToArray(String replaceParam) {
        String[] parmAndTypes = replaceParam.split(",");
        if (parmAndTypes.length == 0) {
            System.out.println("无参数");
            return null;
        }
        String[] params = new String[parmAndTypes.length];

        String type;
        String param;
        for (int i = 0; i < parmAndTypes.length; i++) {
            int leftBracketIndex = parmAndTypes[i].indexOf("(");
            int rightBracketIndex = parmAndTypes[i].indexOf(")");
            type = parmAndTypes[i].substring(leftBracketIndex + 1, rightBracketIndex);
            type = type.trim();
            param = parmAndTypes[i].substring(0, leftBracketIndex);
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
}
