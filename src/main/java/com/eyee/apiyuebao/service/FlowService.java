package com.eyee.apiyuebao.service;

import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.FlowPageReq;


/**
 * Description:
 * Author:jack
 * Date:下午3:53 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface FlowService {
    ResponseBase pageList(FlowPageReq flowPageReq);
}

