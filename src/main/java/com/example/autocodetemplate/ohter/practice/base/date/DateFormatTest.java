package com.example.autocodetemplate.ohter.practice.base.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  ldt format的任何datetimeformat模板时候不会报错  而parse的时候必须模板和传入时间字符串格式必须一致,否则会报错,而且ldt不能解析只有年月日格式的字符串和模板，只能使用ld
 */
public class DateFormatTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String date[] = { "2028-05-12", "3333-05-12", "2028-05-12" , "2028-05-12" , "1111-05-12" , "2028-05-12" , "2028-11-12" , "2028-05-12" };

    public static void main(String[] args) {
//        threadNotSecurity();
        threadSecurity();
    }

    /**
     * simpleDateFormat 线程不安全执行
     */
    public static void threadNotSecurity() {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str1 = date[temp];
                            String str2 = sdf.format(sdf.parse(str1));
                            System.out.println(Thread.currentThread().getName() + ", " + str1 + "," + str2);
                            if(!str1.equals(str2)){
                                throw new RuntimeException(Thread.currentThread().getName()
                                        + ", Expected " + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
                }
            }).start();
        }
}

    /**
     * DateTimeFormatter 线程安全执行
     */
    public static void threadSecurity() {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str1 = date[temp];
                            LocalDate ldt = LocalDate.parse(str1, dateTimeFormatter);

                            String str2 = ldt.format(dateTimeFormatter);
                            System.out.println(Thread.currentThread().getName() + ", " + str1 + "," + str2);
                            if(!str1.equals(str2)){
                                throw new RuntimeException(Thread.currentThread().getName()
                                        + ", Expected " + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
                }
            }).start();
        }
    }
}
