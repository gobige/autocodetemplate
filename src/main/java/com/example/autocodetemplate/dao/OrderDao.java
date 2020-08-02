package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.shardjdbc.TOrder;
import org.apache.ibatis.annotations.*;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;


@Mapper
public interface OrderDao {

    @Transactional
    @ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    @Insert("insert into t_order(user_id,createTime) VALUES (#{user_id},#{createTime});")
    int insert(Integer user_id, Date createTime);


    @Select(value = "select * from t_order where user_id = #{userId} order by createTime")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "orderId", column = "order_id")
    })
    Collection<TOrder> findByUserId(Integer userId);
}
