package com.eyee.apiyuebao.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * Author:jack
 * Date:下午8:30 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
@Entity
@Table(name = "captcha")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mobile;

    private String code;

    private Date createdat;
}
