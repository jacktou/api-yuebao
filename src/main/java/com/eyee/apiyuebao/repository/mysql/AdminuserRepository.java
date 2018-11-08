package com.eyee.apiyuebao.repository.mysql;

import com.eyee.apiyuebao.entity.mysql.Adminuser;
import com.eyee.apiyuebao.entity.mysql.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Description:
 * Author:jack
 * Date:下午8:43 2018/11/7
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface AdminuserRepository extends JpaRepository<Adminuser,Long>, JpaSpecificationExecutor<Adminuser> {

    @Query(value = "select * from adminuser WHERE isdel=0 and username= :username limit 1 ",nativeQuery = true)
    Optional<Adminuser> findByUsername(@Param(value = "username") String username);



    @Transactional
    @Modifying
    @Query(value = " UPDATE  adminuser SET isdel=1 where id= :id",nativeQuery = true)
    int deleteAdminuser(@Param(value = "id") long id);



    @Transactional
    @Modifying
    @Query(value = " UPDATE  adminuser SET userpwd= :userpwd" +
            " , mender= :mender, updatedat= :updatedat where id= :id",nativeQuery = true)
    int updateUserpwd(@Param(value = "id") long id,@Param(value = "userpwd") String userpwd,
     @Param(value = "mender") String mender,
      @Param(value = "updatedat") Date updatedat
    );


    @Query(value = "select * from adminuser WHERE isdel=0 and username= :username and userpwd= :userpwd limit 1 ",nativeQuery = true)
    Optional<Adminuser> findByUsernameAndPwd(@Param(value = "username") String username,@Param(value = "userpwd") String userpwd);



}
