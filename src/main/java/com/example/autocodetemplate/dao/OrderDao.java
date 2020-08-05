package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.shardjdbc.TOrder;
import org.apache.ibatis.annotations.*;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Mapper
public interface OrderDao  {

//    @Transactional
//    @ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    int insertTOrder(TOrder tOrder);

    Collection<TOrder> findByUserId(Integer userId);

    Collection<TOrder> findByUserIdIn(List<Integer> userIds);

    Collection<TOrder> findCreateTimeBetween(@Param("start") Date start, @Param("end") Date end);

}
