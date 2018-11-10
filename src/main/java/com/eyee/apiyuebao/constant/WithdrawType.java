package com.eyee.apiyuebao.constant;

/**
 * Description:
 * Author:jack
 * Date:上午10:44 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public enum WithdrawType {
    UNKNOWN(0, "未知"),ALIPAY(1, "支付宝"),WECHAT(2, "微信");

    private Integer value;
    private String  fieldName;
    WithdrawType(Integer value,String fieldName){this.value=value;this.fieldName=fieldName;}
    public Integer getValue(){return this.value;}
    public String getFieldName(){return this.fieldName;}
    public static WithdrawType valueOf(Integer value){
        switch (value){
            case 1:
                return ALIPAY;
            case 2:
                return WECHAT;

            default:
                return UNKNOWN;
        }
    }
}
