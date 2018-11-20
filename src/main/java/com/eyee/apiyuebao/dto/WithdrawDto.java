package com.eyee.apiyuebao.dto;

import com.eyee.apiyuebao.constant.WithdrawStatus;
import com.eyee.apiyuebao.constant.WithdrawType;
import lombok.Data;

import java.util.Date;


/**
 * Description:
 * Author:jack
 * Date:上午11:25 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Data
public class WithdrawDto {

    private String username;
    private String mobile;
    private String thirdidno;
    private String tradeno;
    private String balancedto;
    private String amountdto;
    private String withdrawType;
    private String paymentinfodto;
    private String withdrawStatus;
    private String comments;
    private Date createdat;
    private Date arrivetime;
    private String withdrawno;



}
