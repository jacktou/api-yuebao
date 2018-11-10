package com.eyee.apiyuebao.repository.mysql;


import com.eyee.apiyuebao.entity.mysql.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Description:
 * Author:jack
 * Date:下午3:46 2018/11/9
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface FlowRepository extends JpaRepository<Flow,Long>, JpaSpecificationExecutor<Flow> {
}
