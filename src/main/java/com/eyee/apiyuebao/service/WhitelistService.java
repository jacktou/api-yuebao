package com.eyee.apiyuebao.service;

import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.request.IpAddReq;

import java.util.List;

/**
 * Description:
 * Author:jack
 * Date:下午8:43 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface WhitelistService {

   Whitelist addWhitelist(IpAddReq ipAddReq);

   boolean isExists(String ip);

   List<Whitelist> getAllIp();

   int deleteWhitelist(long id);

   Whitelist getWhitelist(long id);

   int updateWhitelist(Whitelist whitelist);


}
