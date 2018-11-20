package com.eyee.apiyuebao.service.Imp;

import com.eyee.apiyuebao.dto.WhitelistDto;
import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.WhitelistRepository;
import com.eyee.apiyuebao.request.IpAddReq;
import com.eyee.apiyuebao.request.IpPageReq;
import com.eyee.apiyuebao.service.WhitelistService;
import com.eyee.apiyuebao.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

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
    public Whitelist addWhitelist(IpAddReq ipAddReq,Adminuser loginadminuser) {

        Whitelist whitelist=new Whitelist();
        whitelist.setIp(ipAddReq.getIp());
        whitelist.setName(ipAddReq.getName());
        whitelist.setCreatedat(DateUtil.getNowDate());
        whitelist.setCreator(loginadminuser.getUsername());
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

    @Override
    public Whitelist getByIp(String ip) {
       return whitelistRepository.findByIp(ip).orElse(null);
    }

    @Override
    public ResponseBase pageList(IpPageReq ipPageReq) {

        Pageable pageable = PageRequest.of(ipPageReq.getPage()-1, ipPageReq.getSize(), Sort.Direction.DESC, "id");

        Page<Whitelist> dataPage = whitelistRepository.findAll(
                (Root<Whitelist> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<String> ippath = root.get("ip");
                    Path<String> namepath =root.get("name");
                    Path<Date>   createdatpath =root.get("createdat");
                    Path<Integer> isdelpath=root.get("isdel");
                    List<Predicate> list = new ArrayList<Predicate>();
                    list.add(cb.equal(isdelpath,0));

                    if(StringUtils.isNotBlank(ipPageReq.getIp())) {
                        list.add(cb.like(ippath,"%"+ipPageReq.getIp()+"%"));
                    }
                    if(StringUtils.isNotBlank(ipPageReq.getName())) {
                        list.add(cb.like(namepath,"%"+ipPageReq.getName()+"%"));
                    }

                    if(StringUtils.isNotBlank(ipPageReq.getBegintime())&&StringUtils.isNotBlank((ipPageReq.getEndtime()))) {

                        Date bdat=DateUtil.strToLongDate(ipPageReq.getBegintime())==null?DateUtil.strToShortDate(ipPageReq.getBegintime()):DateUtil.strToLongDate(ipPageReq.getBegintime());
                        Date edat=DateUtil.strToLongDate(ipPageReq.getEndtime())==null?DateUtil.strToShortDate(ipPageReq.getEndtime()):DateUtil.strToLongDate(ipPageReq.getEndtime());
                        list.add(cb.between(createdatpath,bdat,edat));

                    }

                    Predicate[] p = new Predicate[list.size()];
                    return cb.and(list.toArray(p));


                }, pageable);


        Map<String, Object> data = new HashMap<>();
        data.put("page", ipPageReq.getPage());
        data.put("size", ipPageReq.getSize());
        data.put("total", dataPage.getTotalElements());

        List<WhitelistDto> list=new ArrayList<>();
        for(Whitelist item : dataPage.getContent()){

            WhitelistDto whitelistDto=new WhitelistDto();
            BeanUtils.copyProperties(item,whitelistDto);
            list.add(whitelistDto);

        }
        data.put("list",list);


        return ResponseBase.succeeded().setData(data);
    }
}
