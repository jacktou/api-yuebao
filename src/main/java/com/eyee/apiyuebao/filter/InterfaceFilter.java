package com.eyee.apiyuebao.filter;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.constant.CheckSignStatus;
import com.eyee.apiyuebao.entity.mysql.Account;
import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.entity.mysql.Partner;
import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.AccountRepository;
import com.eyee.apiyuebao.repository.mysql.AdminuserRepository;
import com.eyee.apiyuebao.repository.mysql.PartnerRepository;
import com.eyee.apiyuebao.repository.mysql.WhitelistRepository;
import com.eyee.apiyuebao.util.IpUtil;
import com.eyee.apiyuebao.util.JwtUtil;
import com.eyee.apiyuebao.util.SignCheckUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Description:
 * Author:jack
 * Date:上午10:39 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Slf4j
@Configuration
@WebFilter(filterName = "InterfaceFilter", urlPatterns = "/*")
public class InterfaceFilter implements Filter {

    @Autowired
    WhitelistRepository whitelistRepository;
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    AdminuserRepository adminuserRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //检查ip 和checksign
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req=(HttpServletRequest) request;
//        HttpServletResponse res=(HttpServletResponse) response;
//       try {
//
//
//           if ("POST".equals(req.getMethod().toUpperCase())) {
//
//               byte[] bytes = IOUtils.toByteArray(request.getInputStream());
//               String params = new String(bytes, req.getCharacterEncoding());
//               ThreadCache.setPostRequestParams(params);
//               log.info("filer-post请求参数:[params={}]", params);
//
//           } else {
//               log.info("非post请求");
//           }
//           chain.doFilter(request, response);
//       }catch (Exception e){
//           log.error(e.getMessage(),e);
//       }

        try {

            HttpServletRequest httpServletRequest=(HttpServletRequest)request;
            WrappedHttpServletRequest requestWrapper = new WrappedHttpServletRequest((HttpServletRequest) request);
          //检查白名单
            String clientIp= IpUtil.getIpAddr(httpServletRequest);
            if(checkIP(clientIp)) {

                if ("POST".equals(requestWrapper.getMethod().toUpperCase())) {
                    String params = requestWrapper.getRequestParams();
                    log.info("filer-post请求参数:[params={}]", params);

                    //url不包含open关键字需要验证
                    if(requestWrapper.getRequestURL().indexOf("/open")<=-1) {


                        String token = httpServletRequest.getHeader("token");
                        //jwt 验证方式 后端管理
                        if (StringUtils.isNotBlank(token)) {

                            Claims claims = JwtUtil.parseJWT(token);
                            if (claims!=null) {

                                 String username=claims.get("username").toString();
                                Optional<Adminuser> adminuser = adminuserRepository.findByUsername(username);
                                if (!adminuser.isPresent()) {

                                   log.info("toekn:"+token+"用户不存在！");

                                    response.setContentType("application/json;charset=utf-8");
                                    response.getWriter().write(ResponseBase.failed(ApiCode.NOT_FOUND,"toekn:"+token+"用户不存在！").toJsonFailed());

                                }
                                boolean verify = JwtUtil.isVerify(token, adminuser.get());
                                if (!verify) {

                                    log.info("token不正确，非法访问！");
                                    response.setContentType("application/json;charset=utf-8");
                                    response.getWriter().write(ResponseBase.failed(ApiCode.BAD_REQUEST,"toekn:"+token+"验证不通过！").toJsonFailed());
                                    return;
                                }

                                requestWrapper.setAttribute("Adminuser", adminuser.get());
                            }else{

                                response.setContentType("application/json;charset=utf-8");
                                response.getWriter().write(ResponseBase.failed(ApiCode.EXPIRE,"token过期或非法toekn，非法访问！").toJsonFailed());
                                 return;
                            }


                        } else {        //sign认证

                            //检查签名
                            JSONObject jsonObject = JSONObject.parseObject(params);
                            CheckSignStatus checkSignStatus = checkSign(jsonObject);

                            if (checkSignStatus == CheckSignStatus.NORMAL) {


                                //设置用户信息
                                try {
                                    String uid = jsonObject.get("uid").toString();
                                    Account account = accountRepository.getOne(uid);
                                    if (account == null) {

                                        response.setContentType("application/json;charset=utf-8");
                                        response.getWriter().write(ResponseBase.failed(ApiCode.NOT_FOUND, "用户uid:" + uid + "不存在").toJsonFailed());
                                        return;
                                    }

                                    requestWrapper.setAttribute("Account", account);
                                } catch (Exception e) {
                                    log.error(e.getMessage(), e);
                                    response.setContentType("application/json;charset=utf-8");
                                    response.getWriter().write(ResponseBase.failed(ApiCode.SERVICE_EXCEPTION, "请求异常").toJsonFailed());
                                    return;
                                }


                            } else if(checkSignStatus==CheckSignStatus.EXPIRE) {

                                log.info("nosign:" + checkSignStatus.getFieldName());
                                response.setContentType("application/json;charset=utf-8");
                                response.getWriter().write(ResponseBase.failed(ApiCode.NO_SIGN, "签名已过期").toJsonFailed());
                                return;
                            }
                            else if(checkSignStatus==CheckSignStatus.WRONG){
                                log.info("nosign:" + checkSignStatus.getFieldName());
                                response.setContentType("application/json;charset=utf-8");
                                response.getWriter().write(ResponseBase.failed(ApiCode.NO_SIGN, "签名不正确").toJsonFailed());
                                return;

                            }else{
                                log.info("nosign:" + checkSignStatus.getFieldName());
                                response.setContentType("application/json;charset=utf-8");
                                response.getWriter().write(ResponseBase.failed(ApiCode.NO_SIGN, "签名异常").toJsonFailed());
                                return;
                            }

                        }
                    }


                } else {

                    log.info("非post请求");
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(ResponseBase.failed(ApiCode.NO_REQUEST_POST, "接口请求不是POST方式").toJsonFailed());
                    return;
                }


            }else{

                log.info("clientip:"+clientIp+"没有授权");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(ResponseBase.failed(ApiCode.NO_AUTH_IP, "ip:"+request.getAttribute("noauthip")+"未授权").toJsonFailed());
                return;
            }

            WrapperHttpServletResponse responseWrapper=new WrapperHttpServletResponse((HttpServletResponse) response);
            // chain.doFilter(requestWrapper, response);
            chain.doFilter(requestWrapper, responseWrapper);

            byte[] content = responseWrapper.getContent();//获取返回值
            if (content.length > 0)
            {
                String rspStr = new String(content, request.getCharacterEncoding());
                //日志处理：https://blog.csdn.net/qq_42151769/article/details/83278408 采用队列来操作
                log.info(rspStr);
                ServletOutputStream out = response.getOutputStream();
                out.write(rspStr.getBytes());
                out.flush();


            }






        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(ResponseBase.failed(ApiCode.SERVICE_EXCEPTION, "请求异常").toJsonFailed());
            return;

        }

    }

    @Override
    public void destroy() {

    }

    private boolean checkIP(String clientIp){
        List<Whitelist> all = whitelistRepository.findAll();
        log.info("ip length:"+all.size()+" clientip:"+clientIp);
        Optional<Whitelist> first = all.stream().filter(x -> x.getIp().equals(clientIp)).findFirst();
        if(first.isPresent())
        {
            return true;
        }else{
            return false;
        }

    }

    private CheckSignStatus checkSign(JSONObject jsonObject){

       try {
           String partnerid = jsonObject.get("partnerid").toString();
           if (StringUtils.isNotBlank(partnerid)) {

               Partner partner = partnerRepository.findOndByPartnerid(partnerid);
               if (partner != null) {

                   String partnerkey = partner.getPartnerkey();

                   Map<String, Object> map = JSONObject.toJavaObject(jsonObject, Map.class);


                   return SignCheckUtil.checkSign(map, partnerkey);


               } else {
                   return CheckSignStatus.NONE;
               }


           } else {
               return CheckSignStatus.NONE;
           }
       }catch (Exception e){

           return CheckSignStatus.NONE;
       }

    }




}
