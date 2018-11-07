package com.eyee.apiyuebao.dto;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午2:32 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class WhitelistDto {

    private long id;

    private String name;

    private String ip;

    private Date createdat;
}
