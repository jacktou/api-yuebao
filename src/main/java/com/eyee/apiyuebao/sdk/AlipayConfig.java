package com.eyee.apiyuebao.sdk;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author:jack
 * Date:下午8:44 2018/11/16
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Configuration
public class AlipayConfig {

    @Bean
    public AlipayClient alipayClient(){

       String appId="";
       String privateKey="";
       String alipayPublicKey="";
       AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId,privateKey,"json","UTF8",alipayPublicKey,"RSA2");
        return alipayClient;
    }

    @Bean
    public Alipay alipay(AlipayClient alipayClient){ return  new Alipay(alipayClient);}
}
