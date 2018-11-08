package com.eyee.apiyuebao.repository.mysql;

import com.eyee.apiyuebao.entity.mysql.Captcha;

import com.eyee.apiyuebao.entity.mysql.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Description:
 * Author:jack
 * Date:下午8:33 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface CaptchaRepository extends JpaRepository<Captcha,Long> {

    @Query(value = "select * from captcha WHERE  mobile= :mobile AND code= :code order BY createdat DESC limit 1 ",nativeQuery = true)
    Optional<Captcha> findByMobile(@Param(value = "mobile") String mobile,@Param(value = "code") String code);
}
