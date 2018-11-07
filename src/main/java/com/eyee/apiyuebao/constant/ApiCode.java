package com.eyee.apiyuebao.constant;

/**
 * Description:
 * Author:jack
 * Date:下午7:45 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public class ApiCode {

    public final static int OK                      = 1511200;

    public final static int INTERNAL_SERVER_ERROR   = 1511500;
    public final static int BAD_REQUEST             = 1511501;

    public final static int MISS_PARAMETER          = 1511502;
    public final static int INVALID_PARAMETER       = 1511503;
    public final static int UNAUTHORIZED            = 1511504;
    public final static int INVALID_VERIFICATION    = 1511533;

    public final static int CHECK_FAIL              = 1511401;
    public final static int NOT_FOUND_ACTIVITY      = 1511404;
    public final static int NOT_FOUND               = 1511404;

    public final static int EXISTED                 = 1511630;

    public final static int EXISTED_CASHMENT        = 1511635;//有未完成的转账记录

    public final static int HAS_CASHMENT            = 1511636;//重回提现

    public final static int NOT_FOUND_CHANNEL       = 1511620;//未绑定提现账号

    public final static int OUT_OF_STOCK              = 1511901; //库存不足，商品已脱销

    public final static int DB_EXCEPTION       = 10710;
    public final static int DB_NULL_VALUE      = 10711;

    public final static int SERVICE_NOT_START  = 10720;
    public final static int SERVICE_EXCEPTION  = 10721;

    public  final static int ON_SELL  =  10722;

    public final static int DELIVERED_OR_UNPAID = 10723;
    public  final static int PLEDGE_LESS  = 10724;

    public  final static int EXISTED_PARAMETER = 1511505;//物流单号已存在

    public  final static int ORDER_GENERATED = 1511506;//已经生成订单不允许下架

    public final static int CAPTCHA_VALIDATE_FAIL = 1511621;//验证码错误

    public final static int CAPTCHA_INVALID = 1511622;//验证码失效

    public  final static int FREEZE  =  1511507;   //冻结

    public final static int AFTER_SALE = 1511508; //售后订单

    public final static int CUSTOMERNO_EXISTED= 1511631; //已生成售后单号

    public final static int SOLD_OUT=1511900;//已下架

    public final static int CAPTCHA_IS_NOT_NULL = 1511623;//验证码不能为空

    public final static int MOBILE_IS_NOT_NULL = 1511624;//手机号码不能为空

    public final static int UPDATE_CLIENT = 1517999;//手机号码不能为空

    public final static int EXISTED_WITHDRAW = 1511701;//已有正在提现中申请


    public final  static int SPIDER_ERROR = 1511444; //爬取数据失败

    public  final static int NOT_LOGIN = 1511445; //未登录

    public final static int NO_CHANCE = 1511446;//抽奖机会用完

    public final static int RED_PACKET_RECEIVE_NOT = 1511666;//红包已经领取完

    public final static int REPEAT_DRAW= 1511447;//领取过红包

    public final static int UP_LIMIT = 1511448;//达到领取上限

    public final static int FAILE_CACHE = 1511449;//读取缓存失败
}
