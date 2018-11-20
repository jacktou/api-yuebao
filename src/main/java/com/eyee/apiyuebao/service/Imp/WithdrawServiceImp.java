package com.eyee.apiyuebao.service.Imp;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.WithdrawStatus;
import com.eyee.apiyuebao.constant.WithdrawType;
import com.eyee.apiyuebao.dto.AdminuserDto;
import com.eyee.apiyuebao.dto.WithdrawDto;
import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.entity.mysql.Withdraw;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.WithdrawRepository;
import com.eyee.apiyuebao.request.WithdrawPageReq;
import com.eyee.apiyuebao.service.WithdrawService;
import com.eyee.apiyuebao.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Description:
 * Author:jack
 * Date:下午1:47 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Service
public class WithdrawServiceImp implements WithdrawService {
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Override
    public ResponseBase pageList(WithdrawPageReq withdrawPageReq) {

        Pageable pageable = PageRequest.of(withdrawPageReq.getPage()-1, withdrawPageReq.getSize(), Sort.Direction.DESC, "id");

        Page<Withdraw> dataPage = withdrawRepository.findAll(
                (Root<Withdraw> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<String> thirdidnopath = root.get("thirdidno");
                    Path<String> mobilepath =root.get("mobile");
                    Path<Date>   createdatpath =root.get("createdat");
                    Path<Integer> statuspath=root.get("status");
                    List<Predicate> list = new ArrayList<Predicate>();


                    if(StringUtils.isNotBlank(withdrawPageReq.getThirdidno())) {
                        list.add(cb.like(thirdidnopath,"%"+withdrawPageReq.getThirdidno()+"%"));
                    }
                    if(StringUtils.isNotBlank(withdrawPageReq.getMobile())) {
                        list.add(cb.like(mobilepath,"%"+withdrawPageReq.getMobile()+"%"));
                    }
                    if(StringUtils.isNotBlank(withdrawPageReq.getStatus())) {

                        list.add(cb.equal(statuspath,Integer.valueOf(withdrawPageReq.getStatus())));
                    }

                    if(StringUtils.isNotBlank(withdrawPageReq.getBegintime())&&StringUtils.isNotBlank((withdrawPageReq.getEndtime()))) {

                        Date bdat= DateUtil.strToLongDate(withdrawPageReq.getBegintime())==null?DateUtil.strToShortDate(withdrawPageReq.getBegintime()):DateUtil.strToLongDate(withdrawPageReq.getBegintime());
                        Date edat=DateUtil.strToLongDate(withdrawPageReq.getEndtime())==null?DateUtil.strToShortDate(withdrawPageReq.getEndtime()):DateUtil.strToLongDate(withdrawPageReq.getEndtime());
                        list.add(cb.between(createdatpath,bdat,edat));

                    }

                    Predicate[] p = new Predicate[list.size()];
                    return cb.and(list.toArray(p));


                }, pageable);


        Map<String, Object> data = new HashMap<>();
        data.put("page", withdrawPageReq.getPage());
        data.put("size", withdrawPageReq.getSize());
        data.put("total", dataPage.getTotalElements());

        List<WithdrawDto> list=new ArrayList<>();
        for(Withdraw item : dataPage.getContent()){

            WithdrawDto withdrawDto=new WithdrawDto();
            BeanUtils.copyProperties(item,withdrawDto);
            DecimalFormat df=new DecimalFormat("0.00");
            String balancedto = df.format((float) item.getBalance() / 100);
            withdrawDto.setBalancedto(balancedto);
            String amountdto=df.format((float)item.getAmount()/100);
            withdrawDto.setAmountdto(amountdto);
            String paymentinfodto=item.getPaymentinfo();
            try {
                JSONObject jsonObject=JSONObject.parseObject(item.getPaymentinfo());
                paymentinfodto=String.format("实名：%s <br/> 支付宝账号：%s",jsonObject.get("realname").toString(),jsonObject.get("aliaccount").toString());
            }catch(Exception ex){

            }
            withdrawDto.setPaymentinfodto(paymentinfodto);
            withdrawDto.setWithdrawStatus(WithdrawStatus.valueOf(item.getStatus()).getFieldName());
            withdrawDto.setWithdrawType(WithdrawType.valueOf(item.getType()).getFieldName());


            list.add(withdrawDto);

        }
        data.put("list",list);


        return ResponseBase.succeeded().setData(data);

    }
}
