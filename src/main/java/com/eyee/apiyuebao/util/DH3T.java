package com.eyee.apiyuebao.util;



import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * Author:jack
 * Date:上午11:34 2018/11/8
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Slf4j
public class DH3T {

    private static final String SMS_SERVER_URL = "http://www.dh3t.com/json/sms/Submit";
    private static final String SMS_ACCOUNT = "dh26941";
    private static final String SMS_PASSWORD = "7Y6f0Hjv";//使用时需md5加密
    private static final String SMS_SUBCODE = "26941";
    private static final String SMS_SIGN="【EYEE蜂潮】";



    public static boolean sendSms(String mobile,String content){

        boolean result=false;
        String jsonPost=packageData(mobile,content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonPost, headers);
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        try {
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(SMS_SERVER_URL, entity, String.class);
            String body = stringResponseEntity.getBody();

            //{"msgid":"1541649963720","result":"0","desc":"提交成功","failPhones":""}
            log.info(body);

            JSONObject jsonObject = JSONObject.parseObject(body);
            if (jsonObject.get("result").toString().equals("0")) {
                result = true;
            }
        }catch (Exception ex){

            log.error("sendSms",ex.getMessage());
        }

        return result;

    }

    private static String packageData(String mobile,String content){

        String password = null;
        try {
            password = SecurityUtils.md5(SMS_PASSWORD);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String data = "{\"account\":\"" + SMS_ACCOUNT + "\""
                + ",\"password\":\"" + password + "\""
                + ",\"msgid\":\"" + System.currentTimeMillis() + "\""
                + ",\"phones\":\"" + mobile + "\""
                + ",\"content\":\"" + content + "\""
                + ",\"sign\":\"" + SMS_SIGN + "\""
                + ",\"subcode\":\"" + SMS_SUBCODE + "\""
                + "}";
        return data;
    }

}
