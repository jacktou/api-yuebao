package com.eyee.apiyuebao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午9:09 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class DateUtil {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat formatter_short = new SimpleDateFormat("yyyy-MM-dd");
    public static String getStringDate() {
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date getNowDate() {
        Date currentTime = new Date();
       String dateString = formatter.format(currentTime);

        try {
            return  formatter.parse(dateString);
        } catch (ParseException e) {

        }
        return null;
    }

    public static Date strToLongDate(String str){


        try {
            return formatter.parse(str);
        } catch (ParseException e) {


            return null;
        }
    }

    public static Date strToShortDate(String str){


        try {
            return formatter_short.parse(str);
        } catch (ParseException e) {


            return null;
        }
    }

}
