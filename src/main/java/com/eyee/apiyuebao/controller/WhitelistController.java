package com.eyee.apiyuebao.controller;

import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.dto.WhitelistDto;
import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.IdReq;
import com.eyee.apiyuebao.request.IpAddReq;
import com.eyee.apiyuebao.request.IpEditReq;
import com.eyee.apiyuebao.request.IpPageReq;
import com.eyee.apiyuebao.service.WhitelistService;
import com.eyee.apiyuebao.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Description:
 * Author:jack
 * Date:下午8:21 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/whitelist/")
public class WhitelistController {

    @Autowired
    private WhitelistService whitelistService;

    @PostMapping("add")
    public ResponseBase AddIp(@RequestBody IpAddReq ipAddReq){

       if(!whitelistService.isExists(ipAddReq.getIp())) {

           Whitelist whitelist = whitelistService.addWhitelist(ipAddReq);
           if (whitelist != null) {
               return ResponseBase.succeeded();
           } else {
               return ResponseBase.failed(ApiCode.BAD_REQUEST, "add faile");
           }
       }else{
           return ResponseBase.failed(ApiCode.EXISTED, " ip exists in table");
       }

    }

    @PostMapping("getall")
    public ResponseBase getAllIp(){

        List<Whitelist> list=whitelistService.getAllIp();
        return ResponseBase.succeeded().setData(list);
    }


    @PostMapping("delete")
    public ResponseBase deleteIp(@RequestBody IdReq idReq){

        int ret=whitelistService.deleteWhitelist(idReq.getId());
        if(ret>0)
        {
            return ResponseBase.succeeded();
        }else{

            return ResponseBase.failed(ApiCode.BAD_REQUEST, "delete faile");
        }
    }

    @PostMapping("get")
    public ResponseBase getIp(@RequestBody IdReq idReq){

        Whitelist whitelist = whitelistService.getWhitelist(idReq.getId());
        if(whitelist!=null)
        {
            WhitelistDto whitelistDto=new WhitelistDto();
            BeanUtils.copyProperties(whitelist,whitelistDto);
            return ResponseBase.succeeded().setData(whitelistDto);
        }else{

            return ResponseBase.failed(ApiCode.BAD_REQUEST, "query faile");
        }
    }


    @PostMapping("update")
    public ResponseBase updateIp(@RequestBody IpEditReq ipEditReq) {


        Whitelist byIp = whitelistService.getByIp(ipEditReq.getIp());
        if(byIp!=null)
        {
            if(byIp.getId()!=ipEditReq.getId())
            {
                return ResponseBase.failed(ApiCode.EXISTED, "update faile,ip same");
            }
        }

        Whitelist whitelist = new Whitelist();
        BeanUtils.copyProperties(ipEditReq, whitelist);

        whitelist.setMender("test");
        whitelist.setUpdatedat(DateUtil.getNowDate());

        int ret = whitelistService.updateWhitelist(whitelist);

        if (ret > 0) {

            return ResponseBase.succeeded();

        } else {
            return ResponseBase.failed(ApiCode.BAD_REQUEST, "update faile");
        }



    }


    @PostMapping("page")
    public ResponseBase pageList(@RequestBody IpPageReq ipPageReq){
        return whitelistService.pageList(ipPageReq);
    }



}
