package com.eyee.apiyuebao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Description:
 * Author:jack
 * Date:下午4:22 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Slf4j
public class DataSourceConfig {

    @Bean(name = "hikariDataSource")
    @Qualifier("hikariDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource hikariDataSource(){

        log.info("Building hikariDataSource......");
        return DataSourceBuilder.create().type(com.zaxxer.hikari.HikariDataSource.class).build();
    }
}
