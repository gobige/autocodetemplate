package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.UserBodyInfo;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import org.springframework.data.jpa.repository.*;

import javax.transaction.Transactional;


public interface JpaTestDao extends JpaRepository<UserBodyInfo, Long> {

    Page<UserBodyInfo> findByNumGreaterThan(Integer num, Pageable pageable);

    @Transactional
    Collection<UserBodyInfo> deleteByNikeNameEquals(String userName);

    @Transactional
    @Modifying
    // JPA 默认使用JPQL语句在EJB实体中面向对象，属性操作，如需使用原生则nativequery设置为true
    @Query(value = "update user_body_info set nike_name = ?1 where id = ?2",nativeQuery = true)
    int updateNickNameById(String userName,Long id);
}

