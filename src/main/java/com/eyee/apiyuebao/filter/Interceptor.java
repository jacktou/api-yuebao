package com.eyee.apiyuebao.filter;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author:jack
 * Date:下午5:04 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 * 应用场景
 1、日志记录，可以记录请求信息的日志，以便进行信息监控、信息统计等。
 2、权限检查：如登陆检测，进入处理器检测是否登陆，如果没有直接返回到登陆页面。
 3、性能监控：典型的是慢日志。
 https://www.jianshu.com/p/1e8d088c2be9
 https://blog.csdn.net/qq_18495465/article/details/78470939?locationNum=10&fps=1
 */
@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       // RequestUtil.charReader(request);
       // System.out.println("====="+getRequestJson(request)+"=======");
        if(request.getRequestURL().indexOf("/open/")>-1){
            System.out.println("====="+getRequestJson(request)+"=======");
           System.out.println("==========================================1================================================");
           return true;
       }
       else {
           response.setContentType("application/json;charset=utf-8");
           response.getWriter().write(ResponseBase.failed(ApiCode.UNAUTHORIZED, "User ID is required.").toJsonFailed());
           return false;
       }

//        //创建日记实体类
//        LoggerEntity entity = new LoggerEntity();
//        //获得sessionId
//        String sessionId = httpServletRequest.getRequestedSessionId();
//        //请求地址信息
//        String requestURI = httpServletRequest.getRequestURI();
//        //请求参数信息(利用fastJson转换参数)
//        String params = JSON.toJSONString(httpServletRequest.getParameterMap(),
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue);
//        //设置客户端ip
//        entity.setClientIp(LoggerUtil.getCliectIp(httpServletRequest));
//        //设置请求方法
//        entity.setMethod(httpServletRequest.getMethod());
//        //设置请求类型
//        entity.setType(LoggerUtil.getRequestType(httpServletRequest));
//        //设置请求参数
//        entity.setParamData(params);
//        //设置请求地址
//        entity.setUri(requestURI);
//        //设置sessionId
//        entity.setSessionId(sessionId);
//        //设置请求开始时间
//        httpServletRequest.setAttribute(SEND_TIME, System.currentTimeMillis());
//        //设置请求实体到request内,方便afterCompletion调用
//        httpServletRequest.setAttribute("log_entity", entity);




       // return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        System.out.println("==========================================post================================================");
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        System.out.println("==========================================after================================================");

        HandlerMethod h=(HandlerMethod)handler;
        System.out.println(h.getMethod().getName());

//        //请求状态
//        int status = httpServletResponse.getStatus();
//        //当前时间
//        long time = System.currentTimeMillis();
//        //上次请求时间
//        Long requestTime = Long.valueOf(httpServletRequest.getAttribute(SEND_TIME).toString());
//        //获取请求日记的实体
//        LoggerEntity entity = (LoggerEntity) httpServletRequest.getAttribute(DATA);
//        //设置时间差
//        entity.setConsuming(Long.valueOf(time-requestTime).toString());
//        //设置错误码
//        entity.setStatusCode(status+"");
//        //设置返回值
//        entity.setReturnData(JSON.toJSONString(httpServletRequest.getAttribute(LoggerUtil.LOGGER_RETURN),
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue));
//        //通过WebApplicationContextUtils获取loggerDao
//        //LoggerJpa loggerDao = getDao(LoggerJpa.class,httpServletRequest);
//        //将日记写入数据库
//        loggerDao.save(entity);



    }

    private String getRequestJson(HttpServletRequest request) throws Exception{
        String result="[]";
//        MyRequestWrapper myRequestWrapper = null;
//        switch (request.getMethod().toLowerCase()){
//            case "get":
//                Map<String, String> params = new HashMap<>(16);
//                Enumeration<String> pNames = request.getParameterNames();
//                while (pNames.hasMoreElements()){
//                    String pName=pNames.nextElement();
//                    params.put(pName,request.getParameter(pName));
//                }
//                result=JSONObject.toJSONString(params,true);
//                break;
//
//            case "post":
//               // myRequestWrapper=new MyRequestWrapper(request);
//               // String bodyString = myRequestWrapper.getBodyString(request);
//                JSONObject jsonObject = JSONObject.parseObject(new MyRequestWrapper(request).getBodyString(request));
//                result=jsonObject.toJSONString();
//                break;
//                default:
//                    break;
//
//        }
        return result;
    }
}
