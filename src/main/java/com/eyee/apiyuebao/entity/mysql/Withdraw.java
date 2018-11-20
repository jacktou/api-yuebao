package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.ldap.PagedResultsControl;
import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:上午10:53 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "withdraw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String accountid;
    private String withdrawno;
    private String thirdidno;
    private int balance;
    private int amount;
    private int type;
    private String paymentinfo;
    private String tradeno;
    private Date arrivetime;
    private String comments;
    private int status;
    private Date createdat;
    private Date updatedat;
    private String failcause;
    private String username;
    private String mobile;


}
