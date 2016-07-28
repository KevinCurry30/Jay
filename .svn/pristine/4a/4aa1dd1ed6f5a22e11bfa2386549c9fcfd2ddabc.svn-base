package com.diligroup.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjf on 2016/7/20.
 */
public class DateUtils {

    private static int year;
    private static int month;
    private static int day;

    /**
     * 判断date3 距离前两个日期哪个更接近一些
     *
     * @param DATE1
     * @param DATE2
     * @param date3
     * @return
     */
    public static String compare_date(String DATE1, String DATE2, String date3) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            Date dt3 = df.parse(date3);
            Date nearDate = Math.abs(dt3.getTime() - dt1.getTime()) < Math.abs(dt3.getTime() - dt2.getTime()) ? dt1 : dt2;
            String nearStr = df.format(nearDate);
            System.out.println("最近的日期是====" + nearStr);
            System.out.println("两个日期相等吗====" + nearStr.equals(DATE1));
            return nearStr;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期 2016年7月20日
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        return year + "年" + (month + 1) + "月" + day + "日";
    }

    /**
     * 获取当前星期
     *
     * @return
     */
    public static String getWeekDay() {
        Calendar calendar = Calendar.getInstance();
//        int year= calendar.get(Calendar.YEAR);
//        int month= calendar.get(Calendar.MONTH);
//        int day= calendar.get(Calendar.DATE);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (weekDay) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
        }
        return "周" + weekDay;
    }

    // 指定某年中的某月的第一天是星期几
    public static String getWeekdayOfMonth(int year, int month, int day) {
        String[] arr = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return arr[dayOfWeek];
    }

    /**
     * 判断一个日期是不是今天日期
     *
     * @param y
     * @param mon
     * @param d
     * @return
     */
    public static boolean isToday(int y, int mon, int d) {
        getCurrentDate();
        if (year == y && month == mon && day == d) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 推断一个日期一个间隔时间段后的日期
     *
     * @param startdate
     */
    public static String getDate(String startdate, int delayDay) {
        Date date = null;
        try {
            date = (new SimpleDateFormat("yyyy年M月d日")).parse(startdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, delayDay);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String dateStr = new SimpleDateFormat("yyyy年M月d日").format(cal.getTime());
//        if(dateStr.charAt(5)=='0' )
        switch (dayOfWeek) {
            case 0:
                return new SimpleDateFormat("yyyy年M月dd日").format(cal.getTime()) + " 周日";
            case 1:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周一";
            case 2:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周二";
            case 3:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周三";
            case 4:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周四";
            case 5:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周五";
            case 6:
                return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime()) + " 周六";
        }
        return new SimpleDateFormat("yyyy年M月d日").format(cal.getTime());
    }

    /*
    日期格式转换 2016年7月26日 转为 2016-7-26
     */
    public static String dateFormatChagee(String date) {
//    str="2016年7月21日";
        String regex = "\\d?([年])";
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                regex = "\\d?([月])";
            }
            if (i == 2) {
                regex = "\\d?([日])";
            }
            if (i == 0) {
                regex = "\\d?([年])";
            }
            Pattern pat = Pattern.compile(regex);
            Matcher matcher = pat.matcher(date);
            while (matcher.find()) {

                String temp = date.substring(matcher.start(), matcher.end());
                if (i == 0) {
                    date = date.replaceAll(temp, temp.substring(0, temp.lastIndexOf("年")) + "-");
                }
                if (i == 1) {
                    date = date.replaceAll(temp, temp.substring(0, temp.lastIndexOf("月")) + "-");
                }
                if (i == 2) {
                    date = date.replaceAll(temp, temp.substring(0, temp.lastIndexOf("日")) + "");
                }
//            System.out.println(str);
            }

        }
        return date;
    }
}
