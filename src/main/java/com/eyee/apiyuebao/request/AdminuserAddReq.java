package com.eyee.apiyuebao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Author:jack
 * Date:下午9:13 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminuserAddReq extends RequestBase {

    private String username;

    private String mobile;

    private String code;

    private String userpwd;
}
