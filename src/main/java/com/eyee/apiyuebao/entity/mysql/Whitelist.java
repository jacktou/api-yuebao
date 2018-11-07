package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午8:08 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "whitelist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Whitelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String ip;

    private int creator;

    private Date createdat;

    private int mender;

    private Date updatedat;

    private int isdel;


}
