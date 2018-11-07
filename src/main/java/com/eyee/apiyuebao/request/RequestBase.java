package com.eyee.apiyuebao.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description:
 * Author:jack
 * Date:下午7:53 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
//@NotBlank
public class RequestBase {

    //合作伙伴身份标识

    private String partnerid;
    //时间戳 容许客户端请求时间误差不大于5分钟
    private String timestamp;
    //默认为json格式
    private String format;
    //接口版本号
    private String v;
    //api输入参数签名结果
    private String sign;

}
