package com.eyee.apiyuebao.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * Description:
 * Author:jack
 * Date:下午4:40 2018/11/8
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class AdminuserPageReq extends RequestBase {
    @Range(min = 1, max = 100)
    private int page = 1;

    @Range(min = 1, max = 100)
    private int size = 10;

    private String username;

    private String mobile;

    private String begintime;

    private String endtime;
}
