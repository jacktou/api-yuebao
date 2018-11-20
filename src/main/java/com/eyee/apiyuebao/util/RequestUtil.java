package com.eyee.apiyuebao.util;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Description:
 * Author:jack
 * Date:下午8:59 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class RequestUtil {
    public static String charReader(ServletRequest request) {
        String str, wholeStr = null;
        try {
            BufferedReader br = request.getReader();
            wholeStr = "";
            while ((str = br.readLine()) != null) {
                wholeStr += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholeStr;
    }
}
