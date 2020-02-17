package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.UserBodyInfoDao;
import com.example.autocodetemplate.dao.UserBodyInfoSpecificationDao;
import com.example.autocodetemplate.domain.Specification.UserBodyInfoSpecification;
import com.example.autocodetemplate.domain.UserBodyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBodyInfoDaoForJPATest {

    @Resource
    private UserBodyInfoDao userBodyInfoDao;
//
//    @Resource
//    private UserBodyInfoSpecificationDao userBodyInfoSpecificationDao;

    @Test
    public void testJpa()    {
        UserBodyInfo userBodyInfo = new UserBodyInfo();
        userBodyInfo.setNikeName("jpatest");
        System.out.println(userBodyInfoDao.save(userBodyInfo));
        System.out.println(userBodyInfoDao.findAll());

        // 简单查询
        userBodyInfoDao.deleteByNikeNameEquals("jpatest");
        System.out.println(userBodyInfoDao.findById(1L));
        System.out.println(userBodyInfoDao.count());
        System.out.println(userBodyInfoDao.existsById(2L));

        // 分页
        Pageable pageable = new PageRequest(1, 4, new Sort(Sort.Direction.DESC, "id"));
        System.out.println(userBodyInfoDao.findByNumGreaterThan(24, pageable));
        // 自定义sql查询
        System.out.println(userBodyInfoDao.updateNickNameById("JPA UPDATE", 10L));

        // 简单example查询
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        UserBodyInfo dto = new UserBodyInfo();
        dto.setNum(1);
        Example<UserBodyInfo> example = Example.of(dto, matcher);
        userBodyInfoDao.findAll(example, pageable);

//
//        // 使用specilfication
//        userBodyInfoSpecificationDao.findAll(new UserBodyInfoSpecification());


     }
}
