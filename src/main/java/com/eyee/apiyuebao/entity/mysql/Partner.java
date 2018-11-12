package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午4:13 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "partner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String partnerid;
    private String partnerkey;
    private Date createdat;
}
