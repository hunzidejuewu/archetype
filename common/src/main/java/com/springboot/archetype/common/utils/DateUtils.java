package com.springboot.archetype.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 */
public abstract class DateUtils extends org.apache.commons.lang.time.DateUtils {

    public static final org.slf4j.Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final long SECOND_PER_MINUTE = 60L;
    public static final long SECOND_PER_HOUR = 3600L;
    public static final long SECOND_PER_DAY = 86400L;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String DT_LONG = "yyyyMMddHHmmss";

    private String dateFormat = DATE_FORMAT;

    /**
     * =================================================
     * 日期类型转化 字符串类型 转化为 Date类型
     * =================================================
     */
    public static Date parse(String text) throws ParseException {
        return parse(text, DATE_FORMAT);
    }

    public static Date parse(String text, String formatString) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat(formatString);
        return frm.parse(text);
    }

    public static Date getDate(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            log.error("parse date error", e);
        }
        return null;
    }

    public static Date getDate(String date) {
        return getDate(date, DATE_TIME_FORMAT);
    }

    public static Date getExpireDay() {
        return getDate("2038-01-01 23:59:59");
    }


    /**
     * =================================================
     * Date类型 转化为 字符串类型
     * =================================================
     */
    public static String getDate(Date date) {
        return getDate(date, DATE_TIME_FORMAT);
    }

    public static String getDate(Date date, String format) {
        return parse(date, format);
    }

    public static String parse(Date date) {
        return parse(date, DATE_FORMAT);
    }

    public static String parse(Date date, String formatString) {
        return toString(date, formatString);
    }

    public static String toString(Date currentDate, String dateTimeFormat) {
        DateTime dateTime = new DateTime(currentDate);
        return dateTime.toString(dateTimeFormat);
    }

    /**
     * 指定时间的凌晨
     *
     * @param date
     * @return
     */
    public static Date getDateZero(Date date) {
        date = org.apache.commons.lang.time.DateUtils.setHours(date, 0);
        date = org.apache.commons.lang.time.DateUtils.setMinutes(date, 0);
        date = org.apache.commons.lang.time.DateUtils.setSeconds(date, 0);
        date = org.apache.commons.lang.time.DateUtils.setMilliseconds(date, 0);
        return date;
    }

    public static Calendar getDateZeroCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setTimeToBeginningOfDay(cal);
        return cal;
    }

    public static Calendar getDateZeroCalendars(Date date) {
        Calendar cal = getDateZeroCalendar(date);
        setTimeToBeginningDayOfMonth(cal);
        return cal;
    }

    public static Date add(Date date, int zoom, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(zoom, amount);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }


    /**
     * 计算两个日期相隔的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDayCount(Date date1, Date date2) {
        Assert.notNull(date1);
        Assert.notNull(date2);
        Calendar cal1 = getDateZeroCalendar(date1);
        Calendar cal2 = getDateZeroCalendar(date2);
        long times = Math.abs(cal1.getTime().getTime() - cal2.getTime().getTime());
        //加半天，是为了四舍五入
        times += (1000 * 3600 * 12);
        long between_days = times / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期相隔的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthCount(Date date1, Date date2) {
        Assert.notNull(date1);
        Assert.notNull(date2);
        Calendar cal1 = getDateZeroCalendar(date1);
        Calendar cal2 = getDateZeroCalendar(date2);
        int result = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        return Math.abs(result);
    }

    /**
     * 星期三为3，星期天为7
     *
     * @param date
     * @return
     */
    public static int getDayOfWeekNum(Date date) {
        int week = getDayOfWeek(date) - 1;
        return week == 0 ? 7 : week;
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取中国式星期的开始时间(周一为初始日期)
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfChinaWeek(Date date) {
        int weekNumber = getDayOfWeekNum(date);
        return getStartTimeOfOneDay(addDay(date, (1 - weekNumber)));
    }

    /**
     * 获取中国式星期的结束时间(周日为截止日期)
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfChinaWeek(Date date) {
        int weekNumber = getDayOfWeekNum(date);
        return getEndTimeOfOneDay(addDay(date, (7 - weekNumber)));
    }

    /**
     * 获取今天开始时间
     *
     * @return
     */
    public static Date getStartTimeOfToday() {
        return getStartTimeOfOneDay(new Date());
    }

    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Date getStartTimeOfOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setTimeToBeginningOfDay(cal);
        return cal.getTime();
    }


    /**
     * 获取今天结束时间
     *
     * @return
     */
    public static Date getEndTimeOfToday() {
        return getEndTimeOfOneDay(new Date());
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Date getEndTimeOfOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setTimeToEndOfDay(cal);
        return cal.getTime();
    }


    /**
     * 获取前几个月
     *
     * @param date
     * @return
     * @version 1.0
     * @author shiyc
     * @created 2013年10月16日
     */
    public static Date getLastMonthStartDay(Date date, int lastMonthCount) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            int month = calendar.get(Calendar.MONTH);
            calendar.set(Calendar.MONTH, month - lastMonthCount);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    /**
     * 获取当月的起始日期
     *
     * @param date
     * @return
     */
    public static Date getStartDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setTimeToBeginningOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 获取当月的截止日期
     *
     * @param date
     * @return
     */
    public static Date getEndDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setTimeToEndOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 设置每一天的初始时间
     *
     * @param calendar
     */
    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 设置月的天初始化
     *
     * @param calendar
     */
    private static void setTimeToBeginningDayOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    }


    /**
     * 设置每一天的结束时间
     *
     * @param calendar
     */
    private static void setTimeToEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }


    /**
     * 是否是今日
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        Date d = new Date();
        return DateUtils.getDate(date, YYYYMMDD).equals(DateUtils.getDate(d, YYYYMMDD));
    }


    /**
     * 按照月份划分一段时间。
     * 输入:
     * 2013-02-18 到 2013-04-15
     * <p>
     * 结果：
     * 2013-02-18 2013-02-28
     * --------------
     * 2013-03-01 2013-03-31
     * --------------
     * 2013-04-01 2013-04-15
     * --------------
     *
     * @param start 开始时间
     * @param end   结束时间。
     * @return
     */
    public static List<Long[]> splitTime(long start, long end) {
        Preconditions.checkArgument(start > 0 && end > start);
        start = getDate(parse(new Date(start)), DATE_FORMAT).getTime();
        end = getDate(parse(new Date(end)), DATE_FORMAT).getTime();

        List<Long[]> result = Lists.newArrayList();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            long endTime = DateUtils.getEndDateOfMonth(new Date(start)).getTime();
            if (start > end) {
                break;
            }
            if (endTime > end) {
                Long[] timePair = new Long[2];
                timePair[0] = start;
                timePair[1] = end;
                result.add(timePair);
                break;
            } else {
                Long[] timePair = new Long[2];
                timePair[0] = start;
                timePair[1] = endTime;
                result.add(timePair);
                start = DateUtils.getLastMonthStartDay(new Date(start), -1).getTime();
            }
        }
        return result;
    }

    /**
     * 得到某一天上一周的星期天，中国每周是以星期一开始。
     *
     * @return
     */
    public static Date getPreWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 如果当前日期不是周日则自动往后递增日期
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_WEEK, -1);
        }
        return cal.getTime();
    }

}
