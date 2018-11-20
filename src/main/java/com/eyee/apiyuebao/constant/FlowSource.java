package com.eyee.apiyuebao.constant;

/**
 * Description:
 * Author:jack
 * Date:下午3:21 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public enum FlowSource {
    NONE(-1, ""),  CONSUME(1,"消费"), SNEAKERS(2,"球鞋"),WITHDRAW(3,"提现");
    private Integer value;
    private String fieldName;
    FlowSource(Integer value,String fieldName){this.value=value;this.fieldName=fieldName;}

    public Integer getValue(){return this.value;}
    public String getFieldName(){return this.fieldName;}

    public static FlowSource valueOf(Integer value){

        switch (value){
            case 1:
                return CONSUME;
            case 2:
                return SNEAKERS;
            case 3:
                return WITHDRAW;
                default:
                    return NONE;
        }
    }


}
