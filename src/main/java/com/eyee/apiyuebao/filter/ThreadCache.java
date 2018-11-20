package com.eyee.apiyuebao.filter;

/**
 * Description:
 * Author:jack
 * Date:上午10:36 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class ThreadCache {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static String getPostRequestParams(){
        return threadLocal.get();
    }

    public static void setPostRequestParams(String postRequestParams){
        threadLocal.set(postRequestParams);
    }

    public static void removePostRequestParams(){
        threadLocal.remove();
    }


}
