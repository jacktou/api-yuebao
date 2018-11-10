package com.eyee.apiyuebao.filter;

import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.repository.mysql.WhitelistRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
            WrappedHttpServletRequest requestWrapper = new WrappedHttpServletRequest((HttpServletRequest) request);

            if ("POST".equals(requestWrapper.getMethod().toUpperCase())) {
                String params = requestWrapper.getRequestParams();
                log.info("filer-post请求参数:[params={}]", params);
            } else {
                log.info("非post请求");
            }

            List<Whitelist> all = whitelistRepository.findAll();
            log.info("ip length:"+all.size());
            // 这里doFilter传入我们实现的子类
            chain.doFilter(requestWrapper, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        }

    }

    @Override
    public void destroy() {

    }
}
