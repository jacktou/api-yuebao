package com.eyee.apiyuebao.constant;

/**
 * Description:
 * Author:jack
 * Date:上午10:28 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public enum WithdrawStatus  {

    NONE(-1, ""),  PROCESSING(1,"提现中"), FINISHED(2,"提现完成"),FAILED(3,"提现失败");

    private Integer value;
    private String  fieldName;

    WithdrawStatus(Integer value,String fieldName){this.value=value;this.fieldName=fieldName;}

    public Integer getValue(){return this.value;}
    public String getFieldName(){return this.fieldName;}

    public static WithdrawStatus valueOf(Integer value){
        switch (value){
            case 1:
                return PROCESSING;
            case 2:
                return FINISHED;
            case 3:
                return FAILED;
                default:
                    return NONE;
        }
    }
}
