package com.eyee.apiyuebao.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author:jack
 * Date:下午2:25 2018/11/8
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class RandonNumberUtils {

    public static String rand(int length, boolean isletter) {
        StringBuilder buffer = new StringBuilder();
        String g = UUID.randomUUID().toString();

        Pattern p = Pattern.compile(isletter ? "[0-9]" : "[^0-9]");
        Matcher match = p.matcher(g);
        g = match.replaceAll("");

        int lack = length - g.length();
        if (lack >= 0) {
            for (int i = 0; i < lack; i++) {
                buffer.append(isletter ? 'x' : '0');
            }
            buffer.append(g);
        } else {
            byte[] datas = g.getBytes();
            for(int i = 0; i < length; i ++) {
                buffer.append((char)(datas[i]));
            }
        }
        return buffer.toString();
    }
}
