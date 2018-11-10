package com.eyee.apiyuebao.filter;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.model.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author:jack
 * Date:上午11:22 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Component
@Slf4j
public class PlatformInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       // ServletOutputStream out=response.getOutputStream();

        if(request.getRequestURL().indexOf("/open")>-1){


         //   System.out.println("====="+getRequestJson(request)+"=======");
            System.out.println("==========================================1================================================");

            return true;

        }
        else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(ResponseBase.failed(ApiCode.UNAUTHORIZED, "User ID is required.").toJsonFailed());
            return false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        System.out.println("==========================================post================================================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("==========================================after================================================");

        HandlerMethod h=(HandlerMethod)handler;
        System.out.println(h.getMethod().getName());
    }

    private String getRequestJson(HttpServletRequest request) throws Exception{
        String result="[]";

        switch (request.getMethod().toLowerCase()){
            case "get":
                Map<String, String> params = new HashMap<>(16);
                Enumeration<String> pNames = request.getParameterNames();
                while (pNames.hasMoreElements()){
                    String pName=pNames.nextElement();
                    params.put(pName,request.getParameter(pName));
                }
                result= JSONObject.toJSONString(params,true);
                break;

            case "post":
                //String paramsStr = ThreadCache.getPostRequestParams();
                //JSONObject jsonObject = JSONObject.parseObject(params);
                //result=paramsStr;    //jsonObject.toJSONString();
                byte[] bytes = IOUtils.toByteArray(request.getInputStream());
                result = new String(bytes, request.getCharacterEncoding());
                break;
            default:
                break;

        }
        return result;
    }
}
