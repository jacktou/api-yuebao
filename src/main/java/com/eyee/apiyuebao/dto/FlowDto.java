package com.eyee.apiyuebao.dto;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午3:16 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class FlowDto {
    private String username;
    private String mobile;
    private String datano;
    private String balancedto;
    private String amountdto;
    private String sourcedto;
    private String commentsdto;
    private Date createdat;
    private String flowno;
}
