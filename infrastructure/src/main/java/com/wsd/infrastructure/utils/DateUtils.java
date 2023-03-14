package com.wsd.infrastructure.utils;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 吴松达
 * @date 2022/4/18 17:24
 * @Description:
 */
public class DateUtils {
    /**
     * 获取当前时间前一小时的时间
     * @param date
     * @return java.util.Date
     */
    public static Date beforeHourToNowDate(Date date){
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        return calendar.getTime();
    }

    /**
     * 获取当前时间后1小时的时间
     * @param date
     * @return java.util.Date
     */
    public static Date afterHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime();
    }

}
