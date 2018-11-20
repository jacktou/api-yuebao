package com.eyee.apiyuebao.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * Description:
 * Author:jack
 * Date:下午1:42 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class WithdrawPageReq extends RequestBase {
    @Range(min = 1, max = 100)
    private int page = 1;

    @Range(min = 1, max = 100)
    private int size = 10;

    private String thirdidno;

    private String mobile;

    private String status;

    private String begintime;

    private String endtime;
}
