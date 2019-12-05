package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.TransactionTest;
import org.apache.ibatis.annotations.*;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */

@Mapper
public interface TransactionsTestDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "num", column = "num")
    })
    @Select("SELECT * FROM transaction_test WHERE id = #{id}")
    TransactionTest queryTransactionTestById(@Param("id") Integer id);

    @Update("UPDATE transaction_test SET name = #{name},num = #{num} WHERE id = #{id}")
    int updateTransactionTest(@Param("name") String name, @Param("num") Integer num,@Param("id")  Integer id);


}
