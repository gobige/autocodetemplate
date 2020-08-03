package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.shardjdbc.TOrder;
import com.example.autocodetemplate.util.TimeUtil;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {

    @Resource
    private OrderDao orderDao;

    @Test
    public void testInsert() throws Exception {

        for (int i = 1; i < 100; i++) {
            Thread.sleep(500L);
            orderDao.insert(i, new Date());
        }
     }

    @Test
    public void testQueryEq() throws Exception {
        Collection<TOrder> orders = orderDao.findByUserId(2);

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
    }

    @Test
    public void testQueryIn() throws Exception {
        Collection<TOrder> orders = orderDao.findByUserIdIn(Arrays.asList(2, 1, 3));

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
    }
    @Test
    public void testQueryBetween() throws Exception {
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2020, 8, 1), LocalTime.of(15, 29, 00));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2020, 8, 3), LocalTime.of(16, 00, 00));
        Collection<TOrder> orders = orderDao.findCreateTimeBetween(TimeUtil.localDateTimeToDate(start), TimeUtil.localDateTimeToDate(end));

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
    }
}
