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

    public final static int NOT_FOUND               = 1511404;

    public final static int EXISTED                 = 1511630;

    public final static int EXPIRE                 = 1511631; //过期

    public final static int EXISTED_CASHMENT        = 1511635;//有未完成的转账记录

    public final static int HAS_CASHMENT            = 1511636;//重回提现

    public final static int NOT_FOUND_CHANNEL       = 1511620;//未绑定提现账号



    public final static int DB_EXCEPTION       = 10710;
    public final static int DB_NULL_VALUE      = 10711;

    public final static int SERVICE_NOT_START  = 10720;
    public final static int SERVICE_EXCEPTION  = 10721;

    public final static int CAPTCHA_VALIDATE_FAIL = 1511621;//验证码错误

    public final static int CAPTCHA_INVALID = 1511622;//验证码失效

    public final static int CAPTCHA_IS_NOT_NULL = 1511623;//验证码不能为空

    public final static int MOBILE_IS_NOT_NULL = 1511624;//手机号码不能为空

    public final static int CAPTCHA_EXPIRE = 1511625;//验证码过期


    public  final static int NOT_LOGIN = 1511445; //未登录


    public  final static int NO_AUTH_IP=1511446;  //未授权ip

    public  final static  int NO_REQUEST_POST=1511447; //接口请求方法不是POST方式

    public  final static int NO_SIGN=1511448;//签名不正确


}
