package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午7:46 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private String username;
    private String mobile;
    private int balance;
    private int initbalance;
    private String comments;
    private int status;
    private Date createdat;
    private Date updatedat;


}
