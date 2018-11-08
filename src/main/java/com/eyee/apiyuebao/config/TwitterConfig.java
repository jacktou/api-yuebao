package com.eyee.apiyuebao.config;

import com.eyee.apiyuebao.util.SnowflakeIdWorker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author:jack
 * Date:下午9:06 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Component
@ConfigurationProperties(prefix="twitter") //接收application.properties 中的myProps下面的属性
public class TwitterConfig {
    private int snowFlakeWorkerid;
    private int snowFlakeDatacenterid;

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(snowFlakeWorkerid, snowFlakeDatacenterid);

    }

}