package com.example.autocodetemplate.ohter.practice.base;

public class ExceptionTest {

    public static void main(String[] args) {
        String str = "asdf";
        try {
            Integer.valueOf(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("人行征信--提交联合建模接口--返回值类型转换出错【" + str + "】");
        }
    }
}
