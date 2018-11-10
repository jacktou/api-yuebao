package com.eyee.apiyuebao.config;

import com.eyee.apiyuebao.filter.Interceptor;
import com.eyee.apiyuebao.filter.PlatformInterceptor;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description:
 * Author:jack
 * Date:下午5:17 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 * https://blog.csdn.net/Angry_Mills/article/details/79456137
 */
@EnableWebMvc
@Configuration
@Component
public class WebConfig  implements WebMvcConfigurer{

    @Autowired
    PlatformInterceptor platformInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(platformInterceptor).addPathPatterns("/**");
        //.excludePathPatterns("notify","/static/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }



/**
 * 利用fastjson替换掉jackson，且解决中文乱码问题
 * @param converters
 */

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //处理中文乱码问题
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//        WebMvcConfigurer.super.configureMessageConverters(converters);
//
//    }
}
