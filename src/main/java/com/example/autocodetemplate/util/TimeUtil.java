package com.example.autocodetemplate.util;

import java.time.*;
import java.util.Date;

public class TimeUtil {


    /**
     * 获取当天开始的时间
     * @param localDate 传入当天的localDate
     * @return Date
     */
    public static Date getBegintimeOfDay (LocalDate localDate) {
        if(localDate == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.parse("00:00:00"));
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();

        return Date.from(instant);
    }

    /**
     * localDateTime 转换为Date
     * @param ldt LocalDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime ldt) {
        if(ldt == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = ldt.atZone(zone).toInstant();
        Date date = Date.from(instant);

        return date;
    }

    /**
     * localDate 转换为Date
     * @param ld LocalDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate ld) {
        if(ld == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = ld.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);

        return date;
    }

    /**
     * Date转换为localDate
     * @param date Date
     * @return Date
     */
    public static LocalDate dateToLocalDate(Date date) {
        if(date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        return localDateTime.toLocalDate();
    }

    /**
     * Date 转换为 LocalTime
     * @param date Date
     * @return LocalTime
     */
    public static LocalTime dateToLocalTime(Date date) {
        if(date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        return localDateTime.toLocalTime();
    }

    /**
     * Date 转换为 localDateTime
     * @param date Date
     * @return Date
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if(date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);

        return localDateTime;
    }

}
