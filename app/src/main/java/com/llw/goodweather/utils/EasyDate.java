package com.llw.goodweather.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * EasyDate
 *
 * @author llw
 */
public final class EasyDate {

    public static final String STANDARD_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_CN = "yyyy年MM月dd号";
    public static final String HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String HOUR_MINUTE_SECOND_CN = "HH时mm分ss秒";
    public static final String YEAR = "yyyy";
    public static final String MONTH = "MM";
    public static final String DAY = "dd";
    public static final String HOUR = "HH";
    public static final String MINUTE = "mm";
    public static final String SECOND = "ss";
    public static final String MILLISECOND = "SSS";
    public static final String YESTERDAY = "昨天";
    public static final String TODAY = "今天";
    public static final String TOMORROW = "明天";
    public static final String SUNDAY = "星期日";
    public static final String MONDAY = "星期一";
    public static final String TUESDAY = "星期二";
    public static final String WEDNESDAY = "星期三";
    public static final String THURSDAY = "星期四";
    public static final String FRIDAY = "星期五";
    public static final String SATURDAY = "星期六";
    public static final String[] weekDays = {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};

    /**
     * 获取标准时间
     *
     * @return 例如 2021-07-01 10:35:53
     */
    public static String getDateTime() {
        return new SimpleDateFormat(STANDARD_TIME, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取完整时间
     *
     * @return 例如 2021-07-01 10:37:00.748
     */
    public static String getFullDateTime() {
        return new SimpleDateFormat(FULL_TIME, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取年月日(今天)
     *
     * @return 例如 2021-07-01
     */
    public static String getTheYearMonthAndDay() {
        return new SimpleDateFormat(YEAR_MONTH_DAY, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取年月日
     *
     * @return 例如 2021年07月01号
     */
    public static String getTheYearMonthAndDayCn() {
        return new SimpleDateFormat(YEAR_MONTH_DAY_CN, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取年月日
     *
     * @param delimiter 分隔符
     * @return 例如 2021年07月01号
     */
    public static String getTheYearMonthAndDayDelimiter(CharSequence delimiter) {
        return new SimpleDateFormat(YEAR + delimiter + MONTH + delimiter + DAY, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取时分秒
     *
     * @return 例如 10:38:25
     */
    public static String getHoursMinutesAndSeconds() {
        return new SimpleDateFormat(HOUR_MINUTE_SECOND, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取时分秒
     *
     * @return 例如 10时38分50秒
     */
    public static String getHoursMinutesAndSecondsCn() {
        return new SimpleDateFormat(HOUR_MINUTE_SECOND_CN, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取时分秒
     *
     * @param delimiter 分隔符
     * @return 例如 2021/07/01
     */
    public static String getHoursMinutesAndSecondsDelimiter(CharSequence delimiter) {
        return new SimpleDateFormat(HOUR + delimiter + MINUTE + delimiter + SECOND, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取年
     *
     * @return 例如 2021
     */
    public static String getYear() {
        return new SimpleDateFormat(YEAR, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取月
     *
     * @return 例如 07
     */
    public static String getMonth() {
        return new SimpleDateFormat(MONTH, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取天
     *
     * @return 例如 01
     */
    public static String getDay() {
        return new SimpleDateFormat(DAY, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取小时
     *
     * @return 例如 10
     */
    public static String getHour() {
        return new SimpleDateFormat(HOUR, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取分钟
     *
     * @return 例如 40
     */
    public static String getMinute() {
        return new SimpleDateFormat(MINUTE, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取秒
     *
     * @return 例如 58
     */
    public static String getSecond() {
        return new SimpleDateFormat(SECOND, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取毫秒
     *
     * @return 例如 666
     */
    public static String getMilliSecond() {
        return new SimpleDateFormat(MILLISECOND, Locale.CHINESE).format(new Date());
    }

    /**
     * 获取时间戳
     *
     * @return 例如 1625107306051
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 将时间转换为时间戳
     *
     * @param time 例如 2021-07-01 10:44:11
     * @return 1625107451000
     */
    public static long dateToStamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_TIME, Locale.CHINESE);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(date).getTime();
    }

    /**
     * 将时间戳转换为时间
     *
     * @param timeMillis 例如 1625107637084
     * @return 例如 2021-07-01 10:47:17
     */
    public static String stampToDate(long timeMillis) {
        return new SimpleDateFormat(STANDARD_TIME, Locale.CHINESE).format(new Date(timeMillis));
    }

    /**
     * 格林尼治时间转化为常规时间格式
     *
     * @param dateTime "2020-07-16T09:39+08:00"
     * @return
     */
    public static String greenwichupToSimpleTime(String dateTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = df.parse(dateTime);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date date1 = df1.parse(date.toString());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df2.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "转换失败";
    }

    public static String updateTime(String dateTime) {
        String result = null;
        if (dateTime == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            result = sdf.format(new Date());
        } else {
            result = dateTime.substring(11, 16);
            Log.d("dateTime-->", result);
        }
        return result;
    }

    /**
     * 获取今天是星期几
     *
     * @return 例如 星期四
     */
    public static String getTodayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }
        return weekDays[index];
    }

    /**
     * 根据输入的日期时间计算是星期几
     *
     * @param dateTime 例如 2021-06-20
     * @return 例如 星期日
     */
    public static String getWeek(String dateTime) {
        Calendar cal = Calendar.getInstance();
        if ("".equals(dateTime)) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault());
            Date date;
            try {
                date = sdf.parse(dateTime);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
            if (date != null) {
                cal.setTime(new Date(date.getTime()));
            }
        }
        return weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取输入日期的昨天
     *
     * @param date 例如 2021-07-01
     * @return 例如 2021-06-30
     */
    public static String getYesterday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        return new SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault()).format(date);
    }

    /**
     * 获取输入日期的明天
     *
     * @param date 例如 2021-07-01
     * @return 例如 2021-07-02
     */
    public static String getTomorrow(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +1);
        date = calendar.getTime();
        return new SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault()).format(date);
    }

    /**
     * 根据年月日计算是星期几并与当前日期判断  非昨天、今天、明天 则以星期显示
     *
     * @param dateTime 例如 2021-07-03
     * @return 例如 星期六
     */
    public static String getDayInfo(String dateTime) {
        String dayInfo;
        String yesterday = getYesterday(new Date());
        String today = getTheYearMonthAndDay();
        String tomorrow = getTomorrow(new Date());

        if (dateTime.equals(yesterday)) {
            dayInfo = YESTERDAY;
        } else if (dateTime.equals(today)) {
            dayInfo = TODAY;
        } else if (dateTime.equals(tomorrow)) {
            dayInfo = TOMORROW;
        } else {
            dayInfo = getWeek(dateTime);
        }
        return dayInfo;
    }

    /**
     * 获取本月天数
     *
     * @return 例如 31
     */
    public static int getCurrentMonthDays() {
        Calendar calendar = Calendar.getInstance();
        //把日期设置为当月第一天
        calendar.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得指定月的天数
     *
     * @param year  例如 2021
     * @param month 例如 7
     * @return 例如 31
     */
    public static int getMonthDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        //把日期设置为当月第一天
        calendar.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 时间截取
     * @param date 时间
     * @return
     */
    public static String dateSplit(String date) {//2020-08-07
        String result = null;

        String[] array = date.split("-");
        result = Integer.parseInt(array[1]) + "月" + Integer.parseInt(array[2]) + "号";
        return result;
    }

    /**
     * 根据传入的时间显示时间段描述信息
     */
    public static String showTimeInfo(String timeData) {
        String timeInfo;
        int time;

        if (timeData == null || timeData.equals("")) {
            timeInfo = "获取失败";
        } else {
            time = Integer.parseInt(timeData.trim().substring(0, 2));
            if (time >= 0 && time <= 6) {
                timeInfo = "凌晨";
            } else if (time > 6 && time <= 12) {
                timeInfo = "上午";
            } else if (time == 13) {
                timeInfo = "中午";
            } else if (time > 13 && time <= 18) {
                timeInfo = "下午";
            } else if (time > 18 && time <= 24) {
                timeInfo = "晚上";
            } else {
                timeInfo = "未知";
            }
        }
        return timeInfo;
    }

    /**
     * 获取当天接近12点的时间戳
     */
    public static long getTodayTwelveTimestamp() {
        long zero = getTimestamp() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        return new Timestamp(twelve).getTime();
    }
}
