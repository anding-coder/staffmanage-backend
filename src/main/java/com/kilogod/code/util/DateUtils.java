package com.kilogod.code.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author anding
 * @Description 时间工具类
 **/
public class DateUtils {
    public static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter formatymd = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatymdnounline = DateTimeFormat.forPattern("yyyyMMdd");
    public static DateTimeFormatter formatymdhms = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter formatymdhmss = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS");
    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将字符串转换为Date类型
     *
     * @param strDate 需要转换的时间
     * @param pattern 转换的时间格式
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate, String pattern) throws ValidationException {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            throw new ValidationException(e);
        }
    }

    /**
     * 判断时间是否为空
     */
    public static boolean isNotNull(Date date) {
        if ("null".equals(date) || date == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取两时间之差 毫秒
     */
    public static long differ(Date endtime, Date starttime) throws Exception {
        if (DateUtils.isNotNull(endtime) && DateUtils.isNotNull(starttime)) {
            return endtime.getTime() - starttime.getTime();
        } else {
            throw new Exception("时间不能为空！");
        }
    }

    /**
     * 获取两时间相差多少秒
     */
    public static long differSecond(Date endtime, Date starttime) {
        if (DateUtils.isNotNull(endtime) && DateUtils.isNotNull(starttime)) {
            return (endtime.getTime() - starttime.getTime()) / 1000;
        }
        return 0;
    }

    /**
     * 获取时间加毫秒
     */
    public static Date dateAddMilliSecond(Date time,long length) {
        long datetime=time.getTime() + length;
        if (DateUtils.isNotNull(time)) {
            return new Date(datetime);
        }
        return null;
    }

    /**
     * 获取时间减毫秒
     */
    public static Date dateSubMilliSecond(Date time,long length) {
        long datetime=time.getTime() - length;
        if (DateUtils.isNotNull(time)) {
            return new Date(datetime);
        }
        return null;
    }

    /**
     * 时间加天数
     */
    public static Date addDays(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, num);
        Date time = ca.getTime();
        return time;
    }

    /**
     *时间加月数
     */
    public static Date addMonth(Date date,int num){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, num);
        Date time = ca.getTime();
        return time;
    }


    /**
     * 获取时间戳
     */
    public static Long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * @return java.lang.String
     * @Author anding
     * @Date 2020/11/27 14:18
     * @Param [date, pattern]
     * @Description 日期转为字符串
     **/
    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * @return boolean
     * @Author anding
     * @Date 2020/11/25 18:05
     * @Param [date1, date2]
     * @Description 判断两个时间是否相等
     **/
    public static boolean compareTime(Date date1, Date date2) {
        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();
        time1.setTime(date1);
        time1.set(Calendar.HOUR_OF_DAY, 0);
        time1.set(Calendar.MINUTE, 0);
        time1.set(Calendar.SECOND, 0);
        time1.set(Calendar.MILLISECOND, 0);
        time2.setTime(date2);
        time2.set(Calendar.HOUR_OF_DAY, 0);
        time2.set(Calendar.MINUTE, 0);
        time2.set(Calendar.SECOND, 0);
        time2.set(Calendar.MILLISECOND, 0);
        if (time1.equals(time2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return int
     * @Author anding
     * @Date 2020/11/27 14:28
     * @Param [date]
     * @Description 时间转为JuLian
     **/
    public static int dateToJuLian(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR) - 1900;
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        return year * 1000 + dayOfYear;
    }

    /**
     * @Author anding
     * @Date 2020/11/27 14:34
     * @Param [date]
     * @return java.util.Date
     * @Description JuLian转为时间
     **/
    public static Date juLianToDate(int date) {
        int year = (date / 1000) + 1900;
        int dayOfYear = date % 1000;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return calendar.getTime();
    }

    /**
     * @Author anding
     * @Date 2020/11/27 14:35
     * @Param [days]
     * @return java.util.Date
     * @Description excel时间转为时间格式
     **/
    public static Date getDate(int days) {
        Calendar c = Calendar.getInstance();
        c.set(1900, 0, -1);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * @Author anding
     * @Date 2020/12/28 23:12
     * @Description 时间戳除以1000
     **/
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * @Author anding
     * @Date 2021/1/19 18:35
     * @Param [date]
     * @return java.lang.String
     * @Description 将时间转化为cron表达式
     **/
    public static String dateToCron(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /**
     * @Author anding
     * @Date 2021/1/23 12:13
     * @Description 将cron表达式转为日期
     **/
    public static Date getCronToDate(String cron) {
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        Date date;
        try {
            date = sdf.parse(cron);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
}