package com.eyee.apiyuebao.repository.mysql;

import com.eyee.apiyuebao.entity.mysql.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 * Author:jack
 * Date:下午4:18 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface PartnerRepository extends JpaRepository<Partner,Long> {


    Partner findOndByPartnerid(String partnerid);
}
