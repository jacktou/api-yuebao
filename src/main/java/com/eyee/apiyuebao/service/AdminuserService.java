package com.eyee.apiyuebao.service;

import com.eyee.apiyuebao.constant.MsgCodeStatus;
import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.*;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * Description:
 * Author:jack
 * Date:下午9:23 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface AdminuserService {

    ResponseBase addAdminuser(AdminuserAddReq adminuserAddReq,Adminuser loginadminuser);

    ResponseBase sendMsg(String mobile);

    ResponseBase deleteAdminuser(IdReq idReq);

    ResponseBase getAdminuser(IdReq idReq);

    ResponseBase updateAdminuserPwd(AdminuserEditReq adminuserEditReq,Adminuser loginadminuser);

    MsgCodeStatus checkMsgCode(String mobile, String code);

    ResponseBase pageList(AdminuserPageReq adminuserPageReq);

    ResponseBase login(@RequestBody AdminuserLoginReq adminuserLoginReq, HttpServletRequest httpServletRequest);

}
