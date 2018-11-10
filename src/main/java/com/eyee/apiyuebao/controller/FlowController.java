package com.eyee.apiyuebao.controller;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.model.ResponseBase;

import com.eyee.apiyuebao.request.FlowPageReq;
import com.eyee.apiyuebao.service.FlowService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author:jack
 * Date:下午4:06 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@RestController
@RequestMapping("/api/flow/")
public class FlowController {
    @Autowired
    private FlowService flowService;
    @PostMapping("page")
    public ResponseBase pageList(@RequestBody FlowPageReq flowPageReq){

//        byte[] bytes = IOUtils.toByteArray(request.getInputStream());
//        String params = new String(bytes, request.getCharacterEncoding());
//        FlowPageReq flowPageReq= JSONObject.parseObject(params,FlowPageReq.class);
        return flowService.pageList(flowPageReq);
    }
}
