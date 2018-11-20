package com.eyee.apiyuebao.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.CheckSignStatus;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Description:
 * Author:jack
 * Date:下午4:36 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class SignCheckUtil {

    public static final long expiretime = 5;// 5分钟

    public static String makeJsonBody(JSONObject jsonObject, String partnerKey, String partnerId) {
        long timestamp = System.currentTimeMillis() / 1000;
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("format", "json");
        jsonObject.put("v", "1.4");
        jsonObject.put("partnerid", partnerId);
        jsonObject.put("sign", md5(jsonObject.toJSONString(), partnerKey));
        return jsonObject.toJSONString();
    }

    //签名结果：1正确 2 过期 3 不正确
    public static CheckSignStatus checkSign(Map<String, Object> map, String partnerKey) {
        try {
            String timestamp = map.get("timestamp").toString();
            long s = (System.currentTimeMillis() - Long.parseLong(timestamp))/ (1000 * 60);
            //签名过期
            if (s > expiretime) {
                return CheckSignStatus.EXPIRE;

            } else {

                JSONObject jsonObject = new JSONObject();
                for (String key : map.keySet()) {
                    if (key == "sign") {
                        continue;
                    }
                    jsonObject.put(key, map.get(key));
                }
                String makeSign = md5(jsonObject.toJSONString(), partnerKey);
                if (makeSign.equals(map.get("sign"))) {
                    return CheckSignStatus.NORMAL;
                } else {
                    return CheckSignStatus.WRONG;
                }

            }
        } catch (Exception e) {

            return CheckSignStatus.NONE;
        }


    }


    private static String md5(String json, String partnerKey) {

        JSONObject jsonObject = JSONObject.parseObject(json);
        String params = "";
        Map<String, Object> map = new TreeMap<String, Object>(jsonObject);
        Set<String> keys = map.keySet();
        keys.remove("partnerkey");
        for (String key : keys) {
            Object value = jsonObject.get(key);
            if (value instanceof Array || value instanceof JSONArray) continue;
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                if (StringUtils.isBlank(params)) {
                    params = key + "=" + String.valueOf(value);
                } else {
                    params += "&" + key + "=" + String.valueOf(value);
                }
            }
        }
        params += "&partnerkey=" + partnerKey;
        try {
            return SecurityUtils.md5(params);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

}
