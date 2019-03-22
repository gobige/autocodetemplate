package com.example.autocodetemplate.ohter.practice;

import com.example.autocodetemplate.util.TimeUtil;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
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

    @FunctionalInterface
    interface bufferReaderProcessor {
        String process(BufferedReader reader) throws IOException;
    }

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

        LocalDate validPeriod = LocalDate.parse("29991231", DateTimeFormatter.ofPattern("yyyyMMdd"));
        validPeriod.getDayOfMonth();
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


class TimeTest {
    public static void main(String[] args) {


        LocalDate nowdate = LocalDate.now();
        LocalDate test =  nowdate.plusMonths(1);
        System.out.println(nowdate.compareTo(test));
        System.out.println("获取当前日期：" + nowdate);
        System.out.println("获取当前日期的年份:" + nowdate.get(ChronoField.YEAR) + ",月份:" + nowdate.get(ChronoField.MONTH_OF_YEAR) + ",日:" + nowdate.get(ChronoField.DAY_OF_MONTH));
        System.out.println("修改当前时间的年份" + nowdate.withYear(1993));
        System.out.println("修改当前时间的月份" + nowdate.with(ChronoField.MONTH_OF_YEAR, 9));
        System.out.println("修改当前时间的日期" + nowdate.minusDays(9));
        System.out.println("修改当前时间的日期为当前月份最后一天" + nowdate.with(lastDayOfMonth()));
        System.out.println("BASIC_ISO_DATE格式化日期" + nowdate.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_LOCAL_DATE格式化日期" + nowdate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("------------------------------------------------------------");

        LocalTime nowTime = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(nowdate, nowTime);
        System.out.println("获取当前日期，时间" + ldt);
        System.out.println("获取从时间纪元到现在偏移量为8的秒数" + ldt.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println("------------------------------------------------------------");


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("自定义格式化日期,时间" + ldt.format(dateTimeFormatter));
        System.out.println("------------------------------------------------------------");


        // 必须固定格式,才能解析
        LocalTime custTime1 = LocalTime.parse("17:17:40");
        LocalDate custDate1 = LocalDate.parse("1994-09-29");
        LocalDateTime custldt1 = LocalDateTime.of(custDate1, custTime1);
        System.out.println("获取自定义日期，时间1:" + custldt1);

        LocalDate custDate2 = LocalDate.of(2993, 11, 12);
        LocalTime custTime2 = LocalTime.of(1,4,59);
        LocalDateTime custldt2 = LocalDateTime.of(custDate2, custTime2);
        System.out.println("获取自定义日期，时间2:" + custldt2);

        LocalDateTime custldt3 = LocalDateTime.parse("2028-05-12 12:11:07", dateTimeFormatter);
        System.out.println("获取自定义日期，时间3:" + custldt3);

        Duration duration = Duration.between(custldt2, custldt1);
        System.out.println("时间1和时间2的duration：" + duration);
        System.out.println("时间1和时间2的间隔天数：" + Period.between(custDate2, custDate1).getDays());
        System.out.println("时间1是否在时间2之后:" + custldt1.isAfter(custldt2));

        List<Date> dateList = new ArrayList<>();
        dateList.add(TimeUtil.localDateTimeToDate(custldt1));
        dateList.add(TimeUtil.localDateTimeToDate(custldt2));
        dateList.add(TimeUtil.localDateTimeToDate(custldt3));
        Long cutTime = System.currentTimeMillis();
        Date mindate = dateList.stream().filter(date -> date.getTime() > cutTime).min(Comparator.comparing(Date::getTime)).get();
        System.out.println("通过传统date方式比较,从时间1,2,3中获取比指定时间大的最小时间:" + mindate);

        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        localDateTimeList.add(custldt1);
        localDateTimeList.add(custldt2);
        localDateTimeList.add(custldt3);
        LocalDateTime cutDateTime = LocalDateTime.of(LocalDate.now(),LocalTime.now());
        LocalDateTime minDateTime = localDateTimeList.stream().filter(date -> date.isAfter(cutDateTime)).min(Comparator.comparing(Function.identity())).get();
        System.out.println("通过java8的times方式比较,从时间1,2,3中获取比指定时间大的最小时间:" + minDateTime);
        System.out.println("------------------------------------------------------------");

        // 从1970-01-01 00：00：00到当前时间的毫秒值,(获取的是美国时间)
        Instant ins = Instant.now();
        System.out.println("获取时间纪元开始到现在时间" + ins);
        // 设置偏移量
        OffsetDateTime time=ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println("获取时间纪元开始到现在,偏移8小时时间" + time);
        ZoneId romaZone = ZoneId.of("Europe/Rome");
        System.out.println("自定义显示当前时间罗马时区显示 " + ldt.atZone(romaZone));
        // 获取本地默认时区时间
        ZonedDateTime zoneDateTime=ins.atZone(ZoneId.systemDefault());
        System.out.println("获取时间纪元开始到现在,本地时区时间" + zoneDateTime);
    }
}


/**
 * string test
 */
class StringTest {
    public static void main(String[] str) {
        // StringJoiner类的使用
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("str1");sj.add("str2");
        StringJoiner sj2 = new StringJoiner(",", "{", "}");
        sj2.add("str3");sj2.add("str4");
        // 将sj2的元素合并到sj1中
        sj.merge(sj2);
        System.out.println(sj.toString() + sj.length());
    }
}

/**
 * 流练习
 */
class StreamTest {
    public static void main(String[] args) {
        // List中去除某值形成List
        // 准备练习数据
        List<Apple> apples = new ArrayList<Apple>();
//        apples.add(null);
        apples.add(new Apple(7,2,"china","apple1"));
        apples.stream().forEach(bo -> {
            if (Optional.ofNullable(bo).isPresent()) {
                System.out.println(bo.getCountry());
            }
        });

        // 而且通过collect()方法得到的是一个新的集合
        List<String> list = apples.stream().map(Apple::getName).collect(Collectors.toList());
        Optional<Apple> wightThanThree = apples.stream().filter(a -> a.getWight() > 3).findAny();

        apples.add(new Apple(1,2,"china","apple11"));
        apples.add(new Apple(2,3,"china","apple2"));
        apples.add(new Apple(3,1,"brazil","apple3"));
        apples.add(new Apple(4,3,"china","apple4"));
        apples.add(new Apple(5,7,"brazil","apple5"));
        apples.add(new Apple(6,2,"american","apple6"));


        Map<String, Object> trestmap = apples.stream().collect(Collectors.toMap(Apple::getName, Function.identity()));
        trestmap.containsKey("asdf");
        //1.针对重复key的  覆盖之前的value  不加(k,v)->v)遇到重复key时会报错
        //        apples.add(new Apple(6,2,"american","apple6"));
        apples.stream().collect(Collectors.toMap(Apple::getSeqNo, Apple::getName,(k,v)->v));
        //2.value为空,直接存放  不调用map.merge。同样适用于1(key重复的情况)
        apples.stream().collect(Collector.of(HashMap::new, (m, per)->m.put(per.getSeqNo(),per.getName()), (k, v)->v, Collector.Characteristics.IDENTITY_FINISH));

        List<Apple> apples2 = new ArrayList<Apple>();

        apples2.add(new Apple(1,2,"china","apple1"));
        apples2.add(new Apple(4,3,"china","apple4"));
        apples2.add(new Apple(5,2,"brazil","apple5"));
        apples2.add(new Apple(3,3,"american","apple2"));

        Map appleMaps = apples2.stream().collect(Collectors.toMap(Apple::getCountry, Function.identity()));

        List<Apple> apples3 = new ArrayList<Apple>();

        List<Apple> apples4 = null;

        // 过滤 1 Stream流合可以对空集合进行操作，2但是不能对空对象进行操作，3而且通过collect()方法得到的是一个新的集合
        apples3 = apples3.stream().filter(item -> item != null && item.getWight() > 3).collect(Collectors.toList());

        // 过滤 查询某个属性值符合某条件的元素
        apples = apples.stream().filter(apple -> apple.getWight() > 1).collect(Collectors.toList());

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        System.out.println(f.andThen(f2).apply(1)); //(T t) -> after.apply(apply(t));
        System.out.println(f.compose(f2).apply(1));  //(V v) -> apply(before.apply(v));

        // List获取指定**属性**组成新list
        List<String> names = apples.stream().map(Apple::getName).collect(Collectors.toList());

        // List获取指定**属性**组成新set
        Set<String> nameSets = apples.stream().map(Apple::getCountry).collect(Collectors.toSet());

        // List获取指定**属性或对象**组成新map(tomap操作中key值不能重复，value值不能为null，否则会报错)
        Map<Integer, String> map = apples.stream().collect(Collectors.toMap(Apple::getSeqNo, Apple::getName));
//        Map appleMaps = apples.stream().collect(Collectors.toMap(Apple::getName, Function.identity()));

        // List中指定**属性**求和
        Integer sum = apples.stream().collect(Collectors.summingInt(Apple::getWight));

        // 使用户过滤的方式，求两个相同类型集合的**对象**的交集
        List<Apple> Intersections = apples.stream().filter(item -> apples2.contains(item)).collect(Collectors.toList());

        // List中指定**属性的值**最小元素
        Apple minAppple = apples.stream().min(Comparator.comparing(Apple::getWight)).get();

        // 根据某个属性值对集合排序 由小到大
        apples.sort(Comparator.comparing(Apple::getWight));

        // 获取第一个元素(集合元素为0会报错)
        apples.stream().findFirst().get();
        // 根据某个属性值对集合排序 由大到小
        apples.sort(Comparator.comparing(Apple::getWight).reversed());
        // 根据某个属性值对集合排序 先按某属性排序，再按另外某属性再次排序
        apples.sort(Comparator.comparing(Apple::getWight).reversed().thenComparing(Apple::getCountry));

        // 求两个相同类型元素集合的差集
        List<Apple> Differs = apples.stream().filter(item -> !apples2.contains(item)).collect(Collectors.toList());

        // 求去重并集
        apples.addAll(apples2);
        apples.stream().distinct().collect(Collectors.toList());

        //循环集合内元素并做处理
        apples.stream().forEach(System.out::println);

        // 以某属性对集合分组
        Map<String, List<Apple>> result2 = apples.stream().collect(Collectors.groupingBy(Apple::getCountry));

        Map<Integer, Apple> test = new HashMap<>();
        test.remove(1);
    }

}


