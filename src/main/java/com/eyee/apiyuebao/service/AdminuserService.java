package com.eyee.apiyuebao.service;

import com.eyee.apiyuebao.constant.MsgCodeStatus;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.AdminuserAddReq;
import com.eyee.apiyuebao.request.AdminuserEditReq;
import com.eyee.apiyuebao.request.AdminuserPageReq;
import com.eyee.apiyuebao.request.IdReq;


import javax.xml.ws.Response;

/**
 * Description:
 * Author:jack
 * Date:下午9:23 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface AdminuserService {

    ResponseBase addAdminuser(AdminuserAddReq adminuserAddReq);

    ResponseBase sendMsg(String mobile);

    ResponseBase deleteAdminuser(IdReq idReq);

    ResponseBase getAdminuser(IdReq idReq);

    ResponseBase updateAdminuserPwd(AdminuserEditReq adminuserEditReq);

    MsgCodeStatus checkMsgCode(String mobile, String code);

    ResponseBase pageList(AdminuserPageReq adminuserPageReq);

}
