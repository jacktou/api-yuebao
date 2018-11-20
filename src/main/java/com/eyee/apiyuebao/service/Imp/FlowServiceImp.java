package com.eyee.apiyuebao.service.Imp;

import com.alibaba.fastjson.JSONObject;
import com.eyee.apiyuebao.constant.FlowSource;
import com.eyee.apiyuebao.constant.WithdrawStatus;
import com.eyee.apiyuebao.constant.WithdrawType;
import com.eyee.apiyuebao.dto.FlowDto;
import com.eyee.apiyuebao.dto.WithdrawDto;
import com.eyee.apiyuebao.entity.mysql.Flow;
import com.eyee.apiyuebao.entity.mysql.Withdraw;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.repository.mysql.FlowRepository;
import com.eyee.apiyuebao.request.FlowPageReq;
import com.eyee.apiyuebao.service.FlowService;
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
 * Date:下午3:54 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Service
public class FlowServiceImp implements FlowService {

    @Autowired
    private FlowRepository flowRepository;

    @Override
    public ResponseBase pageList(FlowPageReq flowPageReq) {

        Pageable pageable = PageRequest.of(flowPageReq.getPage()-1, flowPageReq.getSize(), Sort.Direction.DESC, "id");

        Page<Flow> dataPage = flowRepository.findAll(
                (Root<Flow> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                    Path<String> datanopath = root.get("datano");
                    Path<String> mobilepath =root.get("mobile");
                    Path<Date>   createdatpath =root.get("createdat");
                    Path<Integer> sourcepath=root.get("source");
                    List<Predicate> list = new ArrayList<Predicate>();


                    if(StringUtils.isNotBlank(flowPageReq.getDatano())) {
                        list.add(cb.like(datanopath,"%"+flowPageReq.getDatano()+"%"));
                    }
                    if(StringUtils.isNotBlank(flowPageReq.getMobile())) {
                        list.add(cb.like(mobilepath,"%"+flowPageReq.getMobile()+"%"));
                    }
                    if(StringUtils.isNotBlank(flowPageReq.getSource())) {

                        list.add(cb.equal(sourcepath,Integer.valueOf(flowPageReq.getSource())));
                    }

                    if(StringUtils.isNotBlank(flowPageReq.getBegintime())&&StringUtils.isNotBlank((flowPageReq.getEndtime()))) {

                        Date bdat= DateUtil.strToLongDate(flowPageReq.getBegintime())==null?DateUtil.strToShortDate(flowPageReq.getBegintime()):DateUtil.strToLongDate(flowPageReq.getBegintime());
                        Date edat=DateUtil.strToLongDate(flowPageReq.getEndtime())==null?DateUtil.strToShortDate(flowPageReq.getEndtime()):DateUtil.strToLongDate(flowPageReq.getEndtime());
                        list.add(cb.between(createdatpath,bdat,edat));

                    }

                    Predicate[] p = new Predicate[list.size()];
                    return cb.and(list.toArray(p));


                }, pageable);


        Map<String, Object> data = new HashMap<>();
        data.put("page", flowPageReq.getPage());
        data.put("size", flowPageReq.getSize());
        data.put("total", dataPage.getTotalElements());

        List<FlowDto> list=new ArrayList<>();
        for(Flow item : dataPage.getContent()){

            FlowDto flowDto=new FlowDto();
            BeanUtils.copyProperties(item,flowDto);
            DecimalFormat df=new DecimalFormat("0.00");
            String balancedto = df.format((float) item.getBalance() / 100);
            flowDto.setBalancedto(balancedto);
            String amountdto=df.format((float)item.getAmount()/100);
            flowDto.setAmountdto(amountdto);
            String commentsdto=item.getComments();
            try {
                JSONObject jsonObject=JSONObject.parseObject(item.getComments());
                commentsdto=jsonObject.get("comments").toString();
            }catch(Exception ex){

            }
            flowDto.setCommentsdto(commentsdto);
            flowDto.setSourcedto(FlowSource.valueOf(item.getSource()).getFieldName());



            list.add(flowDto);

        }
        data.put("list",list);


        return ResponseBase.succeeded().setData(data);
    }
}
