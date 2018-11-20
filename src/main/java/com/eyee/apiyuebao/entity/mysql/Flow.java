package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午3:02 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "flow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int source;
    private String accountid;
    private String flowno;
    private String thirdidno;
    private String datano;
    private int balance;
    private int amount;
    private int type;
    private String comments;
    private int year;
    private int month;
    private int day;
    private String reason;
    private int status;
    private Date createdat;
    private Date updatedat;
    private String username;
    private String mobile;
}
