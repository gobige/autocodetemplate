package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.shardjdbc.TOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {

    @Resource
    private OrderDao orderDao;

    @Test
    public void testInsert() throws Exception {

        for (int i = 1; i < 13; i++) {
            Thread.sleep(500L);
            orderDao.insert(i, new Date());
        }
     }

    @Test
    public void testQuerys() throws Exception {

        Collection<TOrder> orders = orderDao.findByUserId(2);

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));

    }
}
