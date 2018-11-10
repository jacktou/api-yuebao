package com.eyee.apiyuebao.model;

import com.eyee.apiyuebao.constant.ApiCode;

/**
 * Description:
 * Author:jack
 * Date:下午6:11 2018/10/30
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class ResponseBase<T> {

    public int code;
    public int expiretime;
    public String msg;
    public T data;

    public ResponseBase(){

    }

    private ResponseBase(int code,T data,String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    public static ResponseBase succeeded(){ return new ResponseBase(ApiCode.OK,null,null);}

    public static ResponseBase failed(int code,String msg){return new ResponseBase(code,null,msg);}

    public int getCode(){ return code;}

    public ResponseBase setCode(int code){
        this.code=code;
        return this;
    }

    public String getMsg(){ return msg;}

    public ResponseBase setMsg(String msg){
        this.msg=msg;
        return this;
    }

    public T getData(){return  data;}

    public ResponseBase setData(T data){
        this.data=data;
        return this;
    }

    public  int getExpiretime(){return expiretime;}

    public ResponseBase setExpiretime(int expiretime){
        this.expiretime=expiretime;
        return this;
    }


    public String toJsonFailed(){

        StringBuilder sb=new StringBuilder(100);
        sb.append(String.format("{\"code\":%d,\"msg\":\"%s\"}",code,msg));
        return sb.toString();

    }



    @Override
    public String toString() {

        return String.format("ResponseBase[code=%d,msg=%s,expiretime=%d",code,msg,expiretime);
    }
}
