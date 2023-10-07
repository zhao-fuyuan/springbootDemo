package com.example.demo.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final long ONE_MINUTE = 60 * 1000L;
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;
    public static final long ONE_MONTH = 30 * 24 * 60 * 60 * 1000L;
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String formatDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }

    public static String formatDateTime(long timestamp, String pattern) {
        LocalDateTime localDateTime = parseFromTimestamp(timestamp);
        return formatDateTime(localDateTime, pattern);
    }

    public static LocalDateTime parseFromTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取当天开始时间
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天结束时间
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date afterDay(Date date, Integer offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        return cal.getTime();
    }

    public static Date afterHour(Date date, Integer offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, offset);
        return cal.getTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        System.out.println(formatDateTime(LocalDateTime.now(), "MMddHHmmss"));
        System.out.println(formatDateTime(LocalDateTime.now(), YYYY_MM_DD));
    }
}
