package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.JpaTestDao;
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
    private JpaTestDao jpaTestDao;
//
//    @Resource
//    private UserBodyInfoSpecificationDao userBodyInfoSpecificationDao;

    @Test
    public void testJpa()    {
        UserBodyInfo userBodyInfo = new UserBodyInfo();
        userBodyInfo.setNikeName("jpatest");
        System.out.println(jpaTestDao.save(userBodyInfo));
        System.out.println(jpaTestDao.findAll());

        // 简单查询
        jpaTestDao.deleteByNikeNameEquals("jpatest");
        System.out.println(jpaTestDao.findById(1L));
        System.out.println(jpaTestDao.count());
        System.out.println(jpaTestDao.existsById(2L));

        // 分页
        Pageable pageable = new PageRequest(1, 4, new Sort(Sort.Direction.DESC, "id"));
        System.out.println(jpaTestDao.findByNumGreaterThan(24, pageable));
        // 自定义sql查询
        System.out.println(jpaTestDao.updateNickNameById("JPA UPDATE", 10L));

        // 简单example查询
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        UserBodyInfo dto = new UserBodyInfo();
        dto.setNum(1);
        Example<UserBodyInfo> example = Example.of(dto, matcher);
        jpaTestDao.findAll(example, pageable);

//
//        // 使用specilfication
//        userBodyInfoSpecificationDao.findAll(new UserBodyInfoSpecification());


     }
}
