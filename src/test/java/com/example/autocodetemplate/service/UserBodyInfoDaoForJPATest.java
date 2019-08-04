package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.UserBodyInfoDao;
import com.example.autocodetemplate.dao.mongodb.GoodsMongoDao;
import com.example.autocodetemplate.domain.UserBodyInfo;
import com.example.autocodetemplate.domain.mongodb.GoodsMongo;
import com.example.autocodetemplate.domain.mongodb.SupplierMongo;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
 import org.springframework.data.domain.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBodyInfoDaoForJPATest {

    @Resource
    private UserBodyInfoDao userBodyInfoDao;

    @Test
    public void testJpa()    {
        UserBodyInfo userBodyInfo = new UserBodyInfo();
        userBodyInfo.setName("jpatest");

//        userBodyInfoDao.deleteByNameEquals("jpatest");
//        System.out.println(userBodyInfoDao.save(userBodyInfo));
//        System.out.println(userBodyInfoDao.findAll());
//        System.out.println(userBodyInfoDao.findOne(1L));
//        System.out.println(userBodyInfoDao.count());
//        System.out.println(userBodyInfoDao.exists(2L));

        Pageable pageable = new PageRequest(1, 4, new Sort(Sort.Direction.DESC, "id"));
        System.out.println(userBodyInfoDao.findByAgeGreaterThanEqual(24, pageable));

        System.out.println(userBodyInfoDao.updateUserNameById("JPA UPDATE", 1L));


     }
}
