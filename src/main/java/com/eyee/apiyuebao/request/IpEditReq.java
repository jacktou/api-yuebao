package com.eyee.apiyuebao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Author:jack
 * Date:下午2:51 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpEditReq extends RequestBase {

    private long id;

    private String name;

    private String ip;
}
