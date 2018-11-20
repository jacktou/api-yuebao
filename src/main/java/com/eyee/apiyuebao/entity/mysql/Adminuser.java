package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午8:37 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "adminuser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adminuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String userpwd;

    private String mobile;

    private String creator;

    private Date createdat;

    private String mender;

    private Date updatedat;

    private int isdel;

    private String loginip;

    private Date logintime;

}
