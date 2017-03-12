package com.hello2mao.focus.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 获取当前日期
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }
}