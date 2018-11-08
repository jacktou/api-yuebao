package com.eyee.apiyuebao.controller;


import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.*;
import com.eyee.apiyuebao.service.AdminuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:jack
 * Date:下午9:09 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */

@RestController
@RequestMapping("/api/adminuser/")
public class AdminuserController {

    @Autowired
    private AdminuserService adminuserService;

    @PostMapping("add")
    public ResponseBase AddAdminuser(@RequestBody AdminuserAddReq adminuserAddReq){

       return adminuserService.addAdminuser(adminuserAddReq);
    }


    @PostMapping("delete")
    public ResponseBase deleteAdminuser(@RequestBody IdReq idReq){

        return adminuserService.deleteAdminuser(idReq);
    }


    @PostMapping("get")
    public ResponseBase getAdminuser(@RequestBody IdReq idReq){

        return adminuserService.getAdminuser(idReq);
    }

    @PostMapping("updatepwd")
    public ResponseBase updateAdminuserPwd(@RequestBody AdminuserEditReq adminuserEditReq){

        return adminuserService.updateAdminuserPwd(adminuserEditReq);
    }


    @PostMapping("page")
    public ResponseBase pageList(@RequestBody AdminuserPageReq adminuserPageReq){
        return adminuserService.pageList(adminuserPageReq);
    }



    @GetMapping("open/sendmsg")
    public ResponseBase sendMsg(@RequestParam("mobile") String mobile){

      return adminuserService.sendMsg(mobile);

    }








}
