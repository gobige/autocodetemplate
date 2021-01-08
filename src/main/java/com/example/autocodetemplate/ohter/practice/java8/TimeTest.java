package com.example.autocodetemplate.ohter.practice.java8;

import com.example.autocodetemplate.util.TimeUtil;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class TimeTest {
    public static void main(String[] args) {

        System.out.println(new BigDecimal(-2).compareTo(BigDecimal.ZERO));

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
