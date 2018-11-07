package com.eyee.apiyuebao.repository.mysql;

import com.eyee.apiyuebao.entity.mysql.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.SunHints;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Author:jack
 * Date:下午8:24 2018/11/6
 * Right: Copyright (c) 2018
 * Version: v1.0
 */
public interface WhitelistRepository extends JpaRepository<Whitelist,Long> {

    @Query(value = "select * from whitelist WHERE isdel=0 and ip= :ip limit 1 ",nativeQuery = true)
    Optional<Whitelist> findByIp(@Param(value = "ip") String ip);

    @Override
    @Query(value = "select * from whitelist WHERE isdel=0 order BY createdat DESC ",nativeQuery = true)
    List<Whitelist> findAll();

    @Transactional
    @Modifying
    @Query(value = " UPDATE  whitelist SET isdel=1 where id= :id",nativeQuery = true)
    int deleteWhitelist(@Param(value = "id") long id);

    @Transactional
    @Modifying
    @Query(value = " UPDATE  whitelist SET name= :name,ip= :ip, mender= :mender, updatedat= :updatedat where id= :id",nativeQuery = true)
    int updateWhitelist(@Param(value = "id") long id,
                        @Param(value = "name") String name,
                        @Param(value = "ip") String ip,
                        @Param(value = "mender") int mender,
                        @Param(value = "updatedat") Date updatedat
                        );

}
