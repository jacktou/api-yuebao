package com.eyee.apiyuebao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Author:jack
 * Date:下午3:58 2018/11/8
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminuserEditReq extends RequestBase {
    private long id;
    private String mobile;
    private String code;
    private String userpwd;
}
