package com.sdu.fund.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: fundproduct
 * @description: 时间格式化工具类
 * @author: anonymous
 * @create: 2019-11-27 23:03
 **/

public class DateFormatUtil {

    /** 时间格式化：yyyy-MM-dd HH:mm:ss */
    public static SimpleDateFormat FMT_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 时间格式化：yyyy-MM-dd */
    public static SimpleDateFormat FMT_YMD1 = new SimpleDateFormat("yyyy-MM-dd");

    /** 时间格式化：MM/dd/yyyy HH:mm:ss */
    public static SimpleDateFormat FMT_MDYHMS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    /** 时间格式化：MM/dd/yyyy */
    public static SimpleDateFormat FMT_MDY = new SimpleDateFormat("MM/dd/yyyy");

    /** 时间格式化：MM/dd/yyyy */
    public static SimpleDateFormat FMT_YMD2 = new SimpleDateFormat("yyyy/MM/dd");
    /**
     * 将当前时间转成yyyyMMddHHmmssSSS格式
     */
    public synchronized  static String getMilli() {
        SimpleDateFormat  sdf= new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        try {
            Thread.sleep(1);
        } catch (Exception e) {

        }
        return sdf.format(date);
    }

    /**
     * 将当前时间转成yyyyMMddHHmmss格式
     */
    public static String getsecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 将当前时间转成yyyy-MM-dd HH:mm:ss格式
     */
    public static String getTimesecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 将当前时间转成yyyy-MM-dd 格式
     */
    public static String getTimeDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }



//----------------------------------------指定时间处理
    /**
     * 将指定时间转成yyyy-MM-dd 格式
     */
    public static String getTimeDayByString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    /**
     * 将指定时间转成yyyy-MM-dd HH:mm:ss 格式
     */
    public static String getTimeDayByStringDou(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 获取传入时间的 最早时间 00:00:00
     */
    public static Date getDateTop(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date nextDate = cal.getTime();
        return nextDate;
    }


    /**
     * 获取传入时间的 最晚时间 23:59:59
     */
    public static Date getDateLast(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date nextDate = cal.getTime();
        return nextDate;
    }

    /**
     * 时间字符串转时间
     * @param str
     * @return
     */
    public static Date strToDate(String str,SimpleDateFormat format) {
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            date=new Date();
        }
        return date;
    }

    /**
     * 传入时间减1天
     * */
    public static Date getStringBeforeDay(Date datestr){
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(datestr);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        Date date = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return date;
    }

    /*
     * 获取提前某分钟的时间 正数是加时间  负数是减时间
     */
    public static String getTimeNEWDayMinute(Integer minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, minute);
        return sdf.format(nowTime.getTime());
    }


 }

