package com.eyee.apiyuebao.controller;

import com.eyee.apiyuebao.model.ResponseBase;

import com.eyee.apiyuebao.request.WithdrawPageReq;
import com.eyee.apiyuebao.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:jack
 * Date:下午2:24 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@RestController
@RequestMapping("/api/withdraw/")
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;

    @PostMapping("page")
    public ResponseBase pageList(@RequestBody WithdrawPageReq withdrawPageReq){
        return withdrawService.pageList(withdrawPageReq);
    }
}
