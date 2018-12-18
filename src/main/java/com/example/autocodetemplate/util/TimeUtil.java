package com.example.autocodetemplate.util;

import com.example.autocodetemplate.Enum.EnumDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class TimeUtil {


    private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);
    private static final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * 获取该时间当前月的天数
     */
    public static Integer getDaysOfMonth(Date date) {
        if (date == null) {
            logger.error("传入参数date为null,出错!!");
            return null;
        }
        LocalDate localDate = TimeUtil.dateToLocalDate(date);

        return localDate.lengthOfMonth();
    }


    public static void main(String[] args) {
        System.out.println(getDaysOfMonth(new Date()));
    }

    /**
     * 通过给定的格式转换为自定义时间字符串
     * @param enumDateFormat @see com.carhouse.common.web.enums.EnumDateFormat
     * @param date  @see java.util.Date
     * @return 如果【解析失败】或者 【date入参为null】 返回 ""
     */
    public static String dateConvertDateStr(Date date, EnumDateFormat enumDateFormat) {
        DateTimeFormatter dateTimeFormatter = null;

        if (date == null) {
            return "";
        }

        switch (enumDateFormat) {
            case FORMAT_STYLE_1:dateTimeFormatter = dateTimeFormatter1;
                break;
            case FORMAT_STYLE_2:dateTimeFormatter = dateTimeFormatter2;
                break;
            case FORMAT_STYLE_3:dateTimeFormatter = dateTimeFormatter3;
                break;
            case FORMAT_STYLE_4:dateTimeFormatter = dateTimeFormatter4;
                break;
            case FORMAT_STYLE_5:dateTimeFormatter = dateTimeFormatter5;
                break;
            default:
                dateTimeFormatter = null;
                break;
        }

        if (dateTimeFormatter == null) {
            return "";
        }

        try {
            LocalDateTime ldt = TimeUtil.dateToLocalDateTime(date);
            return ldt.format(dateTimeFormatter);
        } catch (Exception e) {
            logger.error("解析时间为字符串形式出错，{}",e.getMessage());
        }

        return "";
    }

    /**
     * 通过给定的格式转换为自定义时间字符串
     * @param dateTimeFormatter @see java.time.format.DateTimeFormatter
     * @param date  @see java.util.Date
     * @return 如果【解析失败】或者 【date入参为null】 返回 ""
     */
    public static String dateConvertDateStr(Date date, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            return "";
        }
        if (date == null) {
            return "";
        }

        try {
            LocalDateTime ldt = TimeUtil.dateToLocalDateTime(date);
            return ldt.format(dateTimeFormatter);
        } catch (Exception e) {
            logger.error("解析时间为字符串形式出错，{}",e.getMessage());
        }

        return "";
    }

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
     * 将时间戳转换为时间
     */
    public static String stampToDateStr(Long s, String pattern) {
        return TimeUtil.dateConvertDateStr(new Date(s),DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 比较两个时间点的前后
     * @param targetTime
     * @param nowTime
     * @return
     */
    public static boolean isEarlyToNow(Date targetTime, Date nowTime) {
        if (!Optional.ofNullable(targetTime).isPresent() || !Optional.ofNullable(nowTime).isPresent()) {
            return false;
        }
        long target = targetTime.getTime();
        long now = nowTime.getTime();

        if(now<=target){
            return  false;
        }else{
            return true;
        }
    }

    /**
     * 判断目标时间是否在时间段以内
     *
     * @param startTime
     * @param endTime
     * @param myTime
     * @return
     */
    public static boolean isBetweenDateTime(Date startTime, Date endTime, Date myTime) {
        if (!Optional.ofNullable(startTime).isPresent()
                || !Optional.ofNullable(endTime).isPresent()
                || !Optional.ofNullable(myTime).isPresent()) {
            return false;
        }
        long startDateTime = startTime.getTime();
        long endDateTime = endTime.getTime();
        long myDateTime = myTime.getTime();

        if (myDateTime >= startDateTime && myDateTime <= endDateTime) {
            return true;
        }

        return false;
    }

    /**
     * 时间相减，并以分钟返回
     * @param subtracted
     * @param subtractor
     * @return
     */
    public static Integer subtractToMinutes(Date subtracted, Date subtractor) {
        if (!Optional.ofNullable(subtracted).isPresent() || !Optional.ofNullable(subtractor).isPresent()) {
            return 0;
        }
        long resultTime = subtracted.getTime() - subtractor.getTime();
        if (resultTime < 0) {
            return 0;
        }
        resultTime /= 1000;
        if (resultTime < 0) {
            return 0;
        }
        return (int) resultTime / 60;
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public static Integer transForMilliSecond(Date date){
        if(date==null) return null;
        return (int)(date.getTime()/1000);
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
