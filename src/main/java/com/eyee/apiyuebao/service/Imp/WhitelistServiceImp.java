package com.eyee.apiyuebao.service.Imp;

import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.WhitelistRepository;
import com.eyee.apiyuebao.request.IpAddReq;
import com.eyee.apiyuebao.service.WhitelistService;
import com.eyee.apiyuebao.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Author:jack
 * Date:下午8:44 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Service
public class WhitelistServiceImp implements WhitelistService {

   @Autowired
   private WhitelistRepository whitelistRepository;
    @Override
    public Whitelist addWhitelist(IpAddReq ipAddReq) {

        Whitelist whitelist=new Whitelist();
        whitelist.setIp(ipAddReq.getIp());
        whitelist.setName(ipAddReq.getName());
        whitelist.setCreatedat(DateUtil.getNowDate());
        return whitelistRepository.save(whitelist);
      }

    @Override
    public boolean isExists(String ip) {
        Optional<Whitelist> objip = whitelistRepository.findByIp(ip);
        if(objip.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Whitelist> getAllIp() {
        return whitelistRepository.findAll();
    }

    @Override
    public int deleteWhitelist(long id) {
        return whitelistRepository.deleteWhitelist(id);
    }

    @Override
    public Whitelist getWhitelist(long id) {
        return whitelistRepository.getOne(id);
    }

    @Override
    public int updateWhitelist(Whitelist whitelist) {

        if(whitelist!=null) {
            return whitelistRepository.updateWhitelist(whitelist.getId(),whitelist.getName(),whitelist.getIp(),whitelist.getMender(),whitelist.getUpdatedat());
        }else {

            return 0;

        }
    }
}
