package com.eyee.apiyuebao.repository.mysql;


import com.eyee.apiyuebao.entity.mysql.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Description:
 * Author:jack
 * Date:上午11:22 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface WithdrawRepository extends JpaRepository<Withdraw,Long>, JpaSpecificationExecutor<Withdraw> {
}
