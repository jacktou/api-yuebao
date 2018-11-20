package com.eyee.apiyuebao.service;

import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.WithdrawPageReq;

/**
 * Description:
 * Author:jack
 * Date:下午1:46 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface WithdrawService {
    ResponseBase pageList(WithdrawPageReq withdrawPageReq);
}
