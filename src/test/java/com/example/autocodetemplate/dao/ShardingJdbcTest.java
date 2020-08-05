package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.shardjdbc.TOrder;
import com.example.autocodetemplate.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

        for (int i = 1; i < 101; i++) {
            Thread.sleep(500L);
            TOrder tOrder = new TOrder();
            tOrder.setCreateTime(new Date());
            tOrder.setIsDelete(0);
            tOrder.setOrderFee(new BigDecimal(21 + i));
            tOrder.setOrderStatus(1);
            tOrder.setUserId(i);
            orderDao.insertTOrder(tOrder);
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

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("分库分表，分库键查询");
        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
    @Test
    public void testQueryBetween() throws Exception {
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2020, 6, 1), LocalTime.of(15, 29, 00));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2020, 7, 1), LocalTime.of(16, 00, 00));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("分库分表，时间范围 归并查询");
        Collection<TOrder> orders = orderDao.findCreateTimeBetween(TimeUtil.localDateTimeToDate(start), TimeUtil.localDateTimeToDate(end));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
    }
    @Test
    public void testQueryFilter() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("分库分表，非分键条件查询 归并查询");
        Collection<TOrder> orders = orderDao.findByFilter(0,new BigDecimal(10));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        orders.stream().forEach(tOrder -> System.out.println(tOrder.getUserId() + "----" + tOrder.getOrderId() + "----" + tOrder.getCreateTime()));
    }
}
