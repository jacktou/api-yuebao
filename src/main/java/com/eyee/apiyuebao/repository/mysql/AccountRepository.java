package com.eyee.apiyuebao.repository.mysql;

import com.eyee.apiyuebao.entity.mysql.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 * Author:jack
 * Date:下午5:30 2018/11/10
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface AccountRepository extends JpaRepository<Account,String> {

}

