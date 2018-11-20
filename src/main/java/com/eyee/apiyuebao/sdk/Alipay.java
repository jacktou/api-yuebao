package com.eyee.apiyuebao.sdk;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author:jack
 * Date:下午8:51 2018/11/16
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class Alipay {
    private AlipayClient alipayClient;
    Alipay(AlipayClient alipayClient){this.alipayClient=alipayClient;}

    //支付宝转账
    public Map<String,Object> fundTransToaccountTransfer(String out_biz_no, String payee_account,double amount ) throws Exception {
         Map<String,Object> map=new HashMap<>();
        DecimalFormat df=new DecimalFormat("0.00");
        AlipayFundTransToaccountTransferRequest request=new AlipayFundTransToaccountTransferRequest();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("out_biz_no",out_biz_no);
        jsonObject.put("payee_type","ALIPAY_LOGONID");
        jsonObject.put("payee_account",payee_account);
        jsonObject.put("amount",df.format(amount));
        request.setBizContent(jsonObject.toJSONString());
        AlipayFundTransToaccountTransferResponse response=alipayClient.execute(request);
        if(response.isSuccess())
        {
            map.put("pass",true);
            map.put("msg","");

        }else{
            map.put("pass",false);
            map.put("msg",response.getMsg()+response.getSubMsg());
        }

        return map;
    }

    //查询支付宝转账
    public Map<String,Object> fundTransOrderQuery(String out_biz_no)  throws Exception {

        Map<String,Object> map=new HashMap<>();
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("out_biz_no",out_biz_no);
        request.setBizContent(jsonObject.toJSONString());
        AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            map.put("out_biz_no",response.getOutBizNo());
            map.put("status",response.getStatus());
            map.put("order_fee",response.getOrderFee());
            map.put("pay_date",response.getPayDate());
            map.put("arrival_time_end",response.getArrivalTimeEnd());
            map.put("fail_reason",response.getFailReason());

        }else{
            map.put("out_biz_no",out_biz_no);
            map.put("status","fail");
            map.put("msg",response.getMsg()+response.getSubMsg());
        }
       return map;
    }
}
