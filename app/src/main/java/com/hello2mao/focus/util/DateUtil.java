package com.hello2mao.focus.util;


import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final long ONE_MINUTE_MILLIONS = 60 * 1000;
    private static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
    private static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;

    /**
     * 获取当前日期
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 获取短时间格式
     *
     * @return
     */
    public static String getShortTime(long millis) {
        String str = "";

        long durTime = System.currentTimeMillis() - millis;

        if (durTime <= ONE_MINUTE_MILLIONS) {
            str = "刚刚";
        } else if (durTime < ONE_HOUR_MILLIONS) {
            str = durTime / ONE_MINUTE_MILLIONS + "分钟前";
        } else if (durTime < ONE_HOUR_MILLIONS * 24) {
            str = durTime / ONE_HOUR_MILLIONS + "小时前";
        } else {
            Date date = new Date(millis);
            str = DateFormat.format("MM-dd HH:mm", date) + "";
        }
        return str;
    }
}