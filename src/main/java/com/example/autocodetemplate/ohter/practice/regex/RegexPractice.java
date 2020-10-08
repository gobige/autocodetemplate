package com.example.autocodetemplate.ohter.practice.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式相关练习
 */
public class RegexPractice {

    public static void main(String[] args) {
        htmlTagTest();
    }

    /**
     * 正数，负数，小数匹配
     *
     * 拆分
     * 1 符号判断 可能有可能没有
     * 2 整数部分 任何数字，
     * 3 小数点和小数部分（同时）可由可无 任何数字
     */
    public static void numTest1() {
        String[] samples = new String[]{"12", "-12", "22.1", "22.033", "0.12", "11.d2", "0" };

        Pattern pattern = Pattern.compile("\\A[-+]?\\d+(?:\\.\\d+)?\\z"); // 使用编译模式需要加 \A和\z

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 正整数匹配
     *
     */
    public static void numTest2() {
        String[] samples = new String[]{"12", "-12", "22.1", "22.033", "0.12", "11.d2", "0" };

        Pattern pattern = Pattern.compile("\\A([1-9]\\d*)\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 手机号匹配 11位
     * 要想严格的匹配手机号码，要把三大运营商的号段都进行匹配 匹配前三位
     *
     */
    public static void phoneNumTest() {
        String[] samples = new String[]{"18881122122", "17711221222", "14311111221", "14711122111", "15211111221",
                "15411221111", "16311112211", "16211111221", "178211111221", "19211221111", "19911112211", "13322"};

        Pattern pattern = Pattern.compile("\\A1(?:3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-7]|8\\d|9[1389])\\d{8}\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 身份证号码匹配
     * 老身份证  15位
     * 新身份证 18位 最后可以使X|x
     * 开头不能为0
     *
     */
    public static void idCardNumTest() {
        String[] samples = new String[]{"50024219940929097x", "500242199409290972", "000242199409290972", "432111224224531"};

        Pattern pattern = Pattern.compile("\\A[1-9]\\d{14}(\\d\\d[0-9xX]|)?\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 邮政编码匹配
     *
     * 6位
     * 开头不能为0
     *
     * 如果是提取文本，则需要使用断言，判断边界位置  ?<!d
     */
    public static void postalCodeTest() {
        String[] samples = new String[]{"112211", "021221", "21112211"};

        Pattern pattern = Pattern.compile("\\A[1-9]\\d{5}\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * QQ号码匹配
     *
     * 不能0开头
     * 总共5到10位数字
     */
    public static void qqNumTest() {
        String[] samples = new String[]{"569484515", "5694845151211", "2111"};

        Pattern pattern = Pattern.compile("\\A[1-9]\\d{4,9}\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 中文字符匹配
     *
     * 中文字符Unicode编码 4E00-9FFF
     * 总共5到10位数字
     */
    public static void chineseCharTest() {
        String[] samples = new String[]{"中文", "5151211", "asdf好", "asdf"};

        Pattern pattern = Pattern.compile("\\A[\\u4E00-\\u9FFF]+\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * IPV4地址
     *
     */
    public static void ipv4Test() {

    }
    /**
     * 日期和时间
     *
     * 若yyyy-mm-dd 格式
     * 年份 数字
     * 月份1-12月
     * 天数 1-31
     *
     * 时间 HH:MM
     *
     * 还可以通过time函数包将其转换为date对象，如果出错表示该格式有误，而且还能解决不同月份 不同天数的问题
     */
    public static void dateTest() {
        String[] samples = new String[]{"2012-10-11", "2012-1-11", "1-2-1", "2012-32-11", "2012-1-41"};

        Pattern pattern = Pattern.compile("\\A\\d{1,4}-(?:1[0-2]|[1-9])-(?:[12]\\d|3[01]|0?\\d)\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 邮箱
     *
     * 分为四部分
     * @前部分 数字，字母，下划线
     * @后部分 数字 字母
     * .后 数字 字母
     *
     */
    public static void emailTest() {
        String[] samples = new String[]{"2ss@ff.com", "2ss@ff.com.cn", "2ssff.com", "@ff.com"};

        Pattern pattern = Pattern.compile("\\A[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
    /**
     * 网页标签提取
     *
     *
     */
    public static void htmlTagTest() {
        String[] samples = new String[]{"<title>this is titile</title>asdfa", "<title>this is titile</title>",
                "<title>this is titile</ti1tle>"};

        Pattern pattern = Pattern.compile("\\A<title>.*?</title>\\z");

        for (String sample : samples) {
            System.out.println(sample + "--match result--" + pattern.matcher(sample).find());
        }
    }
}