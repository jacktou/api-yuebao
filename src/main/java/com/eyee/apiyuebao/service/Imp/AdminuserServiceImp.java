package com.eyee.apiyuebao.service.Imp;

import com.eyee.apiyuebao.constant.ApiCode;
import com.eyee.apiyuebao.constant.MsgCodeStatus;
import com.eyee.apiyuebao.constant.MsgTemplate;
import com.eyee.apiyuebao.dto.AdminuserDto;
import com.eyee.apiyuebao.dto.WhitelistDto;
import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.entity.mysql.Captcha;
import com.eyee.apiyuebao.entity.mysql.Whitelist;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.AdminuserRepository;
import com.eyee.apiyuebao.repository.mysql.CaptchaRepository;
import com.eyee.apiyuebao.request.AdminuserAddReq;
import com.eyee.apiyuebao.request.AdminuserEditReq;
import com.eyee.apiyuebao.request.AdminuserPageReq;
import com.eyee.apiyuebao.request.IdReq;
import com.eyee.apiyuebao.service.AdminuserService;
import com.eyee.apiyuebao.util.DH3T;
import com.eyee.apiyuebao.util.DateUtil;
import com.eyee.apiyuebao.util.RandonNumberUtils;
import com.eyee.apiyuebao.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Description:
 * Author:jack
 * Date:下午9:22 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Service
public class AdminuserServiceImp implements AdminuserService {

    @Autowired
    private AdminuserRepository adminuserRepository;

    @Autowired
    private CaptchaRepository captchaRepository;

    @Override
    public ResponseBase addAdminuser(AdminuserAddReq adminuserAddReq) {

        Optional<Adminuser> byUsername = adminuserRepository.findByUsername(adminuserAddReq.getUsername());
        if(byUsername.isPresent()){
            return ResponseBase.failed(ApiCode.EXISTED,"add faile, username already existed");
        }else{

            Adminuser adminuser=new Adminuser();
            BeanUtils.copyProperties(adminuserAddReq,adminuser);
            adminuser.setCreator("admin");
            adminuser.setCreatedat(DateUtil.getNowDate());

            try {
                adminuser.setUserpwd(SecurityUtils.md5(adminuser.getUserpwd()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return ResponseBase.failed(ApiCode.EXISTED,"add faile, username already existed");

            }
            Adminuser save = adminuserRepository.save(adminuser);
            if(save!=null){
                return ResponseBase.succeeded();
            }else{

                return ResponseBase.failed(ApiCode.BAD_REQUEST, "add faile");
            }



        }
    }

    @Override
    public ResponseBase sendMsg(String mobile) {

       String rnd= RandonNumberUtils.rand(6,false);
       String content=String.format(MsgTemplate.CAPTCHA,rnd,MsgTemplate.EXPIRE_CAPTCHA);
        boolean result = DH3T.sendSms(mobile, content);
        if(result){

            Captcha captcha=new Captcha();
            captcha.setCode(rnd);
            captcha.setMobile(mobile);
            captcha.setCreatedat(DateUtil.getNowDate());
            Captcha captchasave = captchaRepository.save(captcha);
            if(captchasave==null){

                return ResponseBase.failed(ApiCode.BAD_REQUEST,"send sucess but save faile");
            }

            return ResponseBase.succeeded();


        }else{

            return ResponseBase.failed(ApiCode.BAD_REQUEST,"send faile");
        }


    }

    @Override
    public ResponseBase deleteAdminuser(IdReq idReq) {

        int ret=adminuserRepository.deleteAdminuser(idReq.getId());
        if(ret>0){
            return ResponseBase.succeeded();
        }else{
            return ResponseBase.failed(ApiCode.BAD_REQUEST,"delete faile");
        }
    }

    @Override
    public ResponseBase getAdminuser(IdReq idReq) {

        Adminuser one = adminuserRepository.getOne(idReq.getId());
        if(one!=null)
        {
            AdminuserDto adminuserDto=new AdminuserDto();
            BeanUtils.copyProperties(one,adminuserDto);
            return ResponseBase.succeeded().setData(adminuserDto);

        }
       else{
            return ResponseBase.failed(ApiCode.NOT_FOUND,"NOT_FOUND");
        }
    }

    @Override
    public ResponseBase updateAdminuserPwd(AdminuserEditReq adminuserEditReq) {


        MsgCodeStatus msgCodeStatus = checkMsgCode(adminuserEditReq.getMobile(), adminuserEditReq.getCode());
        if(msgCodeStatus==MsgCodeStatus.NORMAL){
            int result=0;
            try {
                 result= adminuserRepository.updateUserpwd(adminuserEditReq.getId(), SecurityUtils.md5(adminuserEditReq.getUserpwd()),"admin",DateUtil.getNowDate());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if(result>0){
                return ResponseBase.succeeded();
            }else{
                return ResponseBase.failed(ApiCode.BAD_REQUEST,"modify password faile");
            }

        }
        if(msgCodeStatus==MsgCodeStatus.EXPIRE){

            return  ResponseBase.failed(ApiCode.CAPTCHA_EXPIRE,"captcha expire");

        }
        if(msgCodeStatus==MsgCodeStatus.NONE){

            return  ResponseBase.failed(ApiCode.NOT_FOUND,"captcha none");
        }
        return ResponseBase.succeeded();

    }

    @Override
    public MsgCodeStatus checkMsgCode(String mobile, String code) {

        Optional<Captcha> captcha = captchaRepository.findByMobile(mobile, code);
        if(captcha.isPresent()) {

            Date createdat = captcha.get().getCreatedat();
            Date now=DateUtil.getNowDate();
           if((now.getTime()-createdat.getTime())/1000>MsgTemplate.EXPIRE_CAPTCHA*60){

               return MsgCodeStatus.EXPIRE;
           }else
           {
               return MsgCodeStatus.NORMAL;
           }


        }else{

            return MsgCodeStatus.NONE;
        }


    }

    @Override
    public ResponseBase pageList(AdminuserPageReq adminuserPageReq) {
        Pageable pageable = PageRequest.of(adminuserPageReq.getPage()-1, adminuserPageReq.getSize(), Sort.Direction.DESC, "id");

        Page<Adminuser> dataPage = adminuserRepository.findAll(
                (Root<Adminuser> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<String> usernamepath = root.get("username");
                    Path<String> mobilepath =root.get("mobile");
                    Path<Date>   createdatpath =root.get("createdat");
                    Path<Integer> isdelpath=root.get("isdel");
                    List<Predicate> list = new ArrayList<Predicate>();
                    list.add(cb.equal(isdelpath,0));

                    if(StringUtils.isNotBlank(adminuserPageReq.getUsername())) {
                        list.add(cb.like(usernamepath,"%"+adminuserPageReq.getUsername()+"%"));
                    }
                    if(StringUtils.isNotBlank(adminuserPageReq.getMobile())) {
                        list.add(cb.like(mobilepath,"%"+adminuserPageReq.getMobile()+"%"));
                    }

                    if(StringUtils.isNotBlank(adminuserPageReq.getBegintime())&&StringUtils.isNotBlank((adminuserPageReq.getEndtime()))) {

                        Date bdat=DateUtil.strToLongDate(adminuserPageReq.getBegintime())==null?DateUtil.strToShortDate(adminuserPageReq.getBegintime()):DateUtil.strToLongDate(adminuserPageReq.getBegintime());
                        Date edat=DateUtil.strToLongDate(adminuserPageReq.getEndtime())==null?DateUtil.strToShortDate(adminuserPageReq.getEndtime()):DateUtil.strToLongDate(adminuserPageReq.getEndtime());
                        list.add(cb.between(createdatpath,bdat,edat));

                    }

                    Predicate[] p = new Predicate[list.size()];
                    return cb.and(list.toArray(p));


                }, pageable);


        Map<String, Object> data = new HashMap<>();
        data.put("page", adminuserPageReq.getPage());
        data.put("size", adminuserPageReq.getSize());
        data.put("total", dataPage.getTotalElements());

        List<AdminuserDto> list=new ArrayList<>();
        for(Adminuser item : dataPage.getContent()){

            AdminuserDto adminuserDto=new AdminuserDto();
            BeanUtils.copyProperties(item,adminuserDto);
            list.add(adminuserDto);

        }
        data.put("list",list);


        return ResponseBase.succeeded().setData(data);
    }
}
