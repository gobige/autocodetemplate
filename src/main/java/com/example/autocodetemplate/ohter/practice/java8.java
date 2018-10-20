package com.example.autocodetemplate.ohter.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-club</p>
 * <p>ModuleID: XXX</p>
 * <p>Comments: XXXXXXXXXXXXX</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class java8 {
    /**
     * 函数式接口
     * 就是只定义一个抽象方法的接口。你已经知道了Java API中的一些其他函数式接口，如我们在第2章中谈到的Comparator和Runnable。
     * <p>
     * Lambda表达式允许你直接以内联的形式为函数式接口的抽象在哪里以及如何使用方法提供实现，并把整个表达式作为函数式接口的实例
     * 函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符
     * 但是泛型（比如Consumer<T>中的T）只能绑定到
     * 引用类型
     * Predicate  return boolean
     * Consumer   return void
     * Function   return obj
     */

    /**
     * FunctionalInterface test
     * @param brp
     * @return
     * @throws IOException
     */
    public static String processFile(bufferReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("c:暂存/test.txt"))) {
            return brp.process(br);
        }
    }

    public static void main(String[] args) {
        try {
            String online = processFile((BufferedReader br) -> br.readLine());
        } catch (Exception e) {

        }
        List<Apple> list = new ArrayList<Apple>();
        Apple a1 = new Apple();
        a1.setWight(100);
        a1.setCountry("chiana");
        Apple a2 = new Apple();
        a2.setWight(50);
        a2.setCountry("japan");
        Apple a3 = new Apple();
        a3.setWight(500);
        a3.setCountry("danmai");
        Apple a4 = new Apple();
        a4.setWight(500);
        a4.setCountry("Australian");

        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.sort(Comparator.comparing(Apple::getWight));
        list.sort(Comparator.comparing(Apple::getWight).reversed());
        list.sort(Comparator.comparing(Apple::getWight).reversed().thenComparing(Apple::getCountry));
        System.out.println(list);

        List<Apple> apples = list.stream().filter(apple -> apple.getWight() > 100).collect(Collectors.toList());
        Map appleMaps = list.stream().filter(apple -> apple.getWight() > 100).collect(Collectors.toMap(Apple::getCountry, Function.identity()));
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        System.out.println(f.andThen(f2).apply(1)); //(T t) -> after.apply(apply(t));
        System.out.println(f.compose(f2).apply(1));  //(V v) -> apply(before.apply(v));
        Http http = new Http("http", "userToken", "www.imokenet.com", "name", "yates");
        Function<String, String> urlStr = Http::addProtocol;


        Function<String, String> urlStr2 = urlStr.andThen(Http::addHeader).andThen(Http::addUrl);
        // TODO 咋个输出String
        System.out.println(urlStr2.apply("1").toString());


        LocalDate nowdate = LocalDate.now();
        System.out.println("localdate" + nowdate.get(ChronoField.YEAR));
        LocalTime nowTime = LocalTime.parse("17:17:40");
        LocalDateTime ldt = LocalDateTime.of(nowdate, nowTime);
        System.out.println("localDataTime----" + ldt);
        Instant.now();
        System.out.println("instant machine" + Instant.now());
        LocalDate nowOld = LocalDate.of(1993, 9, 29);
        LocalTime nowTimeOld = LocalTime.parse("17:17:40");
        LocalDateTime ldtOld = LocalDateTime.of(nowOld, nowTimeOld);
        System.out.println("ldtOld-----" + ldtOld);
        System.out.println("1993-09-29T17:17:40 long型数据为-----" + ldtOld.toEpochSecond(ZoneOffset.of("+8")));

        Duration duration = Duration.between(ldt, ldtOld);
        System.out.println("duration--------" + duration);
        System.out.println("period------" + Period.between(nowdate, nowOld));
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {

        }

        System.out.println("instant machine " + Instant.now());
        System.out.println("localdate withyear " + nowdate.withYear(1993));
        System.out.println("localdate with month " + nowdate.with(ChronoField.MONTH_OF_YEAR, 9));
        System.out.println("localdate mins days " + nowdate.minusDays(9));
        System.out.println("localdate with last of the month " + nowOld.with(lastDayOfMonth()));
        System.out.println("BASIC_ISO_DATE" + nowdate.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_LOCAL_DATE" + nowdate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("user_defined_formatter" + nowdate.format(dateTimeFormatter));
        ZoneId romaZone = ZoneId.of("Europe/Rome");
        System.out.println("Europe/Rome time is " + ldt.atZone(romaZone));

        Optional<Apple> optional = Optional.empty();
        Apple apple = new Apple();
        Optional<Apple> optional1 = Optional.of(apple);

        // null传入
        DateTimeFormatter dateTimeFormattertwo = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startTime;
        LocalDateTime endTime;
        LocalDateTime nowTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        startTime = LocalDateTime.parse("", dateTimeFormattertwo);
        if (nowTime2.isAfter(startTime)) {
            System.out.println("yes nowtime is after starttime!!!!!!!!!!!!!!!!!!!");
        }


        // 获取比当前时间大的最小的时间
        List<Date> dateList = new ArrayList<>();

        Date mindate = dateList.stream().filter(date -> date.getTime() > System.currentTimeMillis()).min(Comparator.comparing(Date::getTime)).get();
    }
}

class Http {
    Http(String protocol, String header, String url, String param, String value) {
        this.protocol = protocol;
        this.header = header;
        this.url = url;
        this.param = param;
        this.value = value;
    }

    public static String protocol;
    public static String header;
    public static String url;
    public static String param;
    public static String value;

    public static String addProtocol(String protocol) {
        return protocol;
    }

    public static String addHeader(String header) {
        return header;
    }

    public static String addUrl(String url) {
        return url;
    }

    public static String addParam(String param) {
        return param;
    }

    public static String addValue(String value) {
        return value;
    }
}


@FunctionalInterface
interface bufferReaderProcessor {
    String process(BufferedReader reader) throws IOException;
}


class testStringJoiner {
    public static void main(String[] str) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("is");
        sj.add("test");
        StringJoiner sj2 = new StringJoiner(",", "{", "}");
        sj2.add("yes");
        sj2.add("work");
        sj.merge(sj2);
        System.out.println(sj.toString() + sj.length());
    }
}

class testNullStreamPut {
    public static void main(String[] args) {
        List<Integer> testList = null;
        List<Integer> outPutStream = testList.stream().filter(integer -> integer > 0).collect(Collectors.toList());
        System.out.println(outPutStream.isEmpty());
    }

}


class StreamTest {
    public static void main(String[] args) {
        // List中去除某值形成List
        // 准备练习数据
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(1,2,"china"));
        apples.add(new Apple(2,3,"china"));
        apples.add(new Apple(3,1,"brazil"));
        apples.add(new Apple(4,3,"china"));
        apples.add(new Apple(5,2,"brazil"));
        apples.add(new Apple(6,2,"american"));

        List<Apple> apples2 = new ArrayList<Apple>();
        apples.add(new Apple(1,2,"china"));
        apples.add(new Apple(2,11,"spanish"));
        apples.add(new Apple(3,4,"brazil"));
        apples.add(new Apple(3,3,"american"));

        List<Apple> apples3 = new ArrayList<Apple>();

        List<Apple> apples4 = null;

        // 过滤 1 Stream流合可以对空集合进行操作，2但是不能对空对象进行操作，3而且通过collect()方法得到的是一个新的集合
        apples3 = apples3.stream().filter(item -> item != null && item.getWight() > 3).collect(Collectors.toList());

        // List获取指定**属性**组成新list
        List<String> names = apples.stream().map(Apple::getName).collect(Collectors.toList());

        // List获取指定**属性**组成新set
        Set<String> nameSets = apples.stream().map(Apple::getCountry).collect(Collectors.toSet());

        // List获取指定**属性或对象**组成新set
        Map<Integer, String> map = apples.stream().collect(Collectors.toMap(Apple::getSeqNo, Apple::getName));

        // List中指定**属性**求和
        Integer sum = apples.stream().collect(Collectors.summingInt(Apple::getWight));

        // 求两个相同类型元素集合的交集
        List<Apple> Intersections = apples.stream().filter(item -> apples2.contains(item)).collect(Collectors.toList());

        // List中指定**属性**最小元素
        Apple minAppple = apples.stream().min(Comparator.comparing(Apple::getWight)).get();

        // 求两个相同类型元素集合的差集
        List<Apple> Difference = apples.stream().filter(item -> !apples2.contains(item)).collect(Collectors.toList());
        // 求去重并集
        apples.addAll(apples2);
        apples.stream().distinct().collect(Collectors.toList());

        //循环输出
        apples.stream().forEach(System.out::println);

        // 分组
        Map<String, List<Apple>> result2 = apples.stream().collect(Collectors.groupingBy(Apple::getCountry));

    }

}


