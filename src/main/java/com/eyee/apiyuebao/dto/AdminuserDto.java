package com.eyee.apiyuebao.dto;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午3:34 2018/11/8
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class AdminuserDto {

    private long id;

    private String username;

    private String mobile;

    private String creator;

    private String mender;

    private Date createdat;

    private Date updatedat;

}
