package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.UserBodyInfo;
import com.example.autocodetemplate.provider.UserBodyInfoSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.Collection;

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
            @Result(property = "nikeName", column = "nike_name"),
            @Result(property = "num", column = "num")
    })
    @Select("SELECT * FROM user_body_info")
    Collection<UserBodyInfo> queryAll();

    @Select("SELECT * FROM user_body_info")
    Collection<UserBodyInfo> pageQueryrowBounds(RowBounds rowBounds);

    @Select("SELECT * FROM user_body_info")
    Collection<UserBodyInfo> pageQueryrowBounds2(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "nikeName", column = "nike_name"),
            @Result(property = "num", column = "num")
    })
    @Select("SELECT * FROM user_body_info WHERE id = #{id}")
    UserBodyInfo queryTransactionTestById(@Param("id") Integer id);

    @SelectProvider(type = UserBodyInfoSqlProvider.class, method = "queryById")
    UserBodyInfo queryByIdBySelectProvider(@Param("id") Integer id);
    /**
     *
     * @param id
     * @return
     */
    UserBodyInfo queryById(@Param("id") Integer id);

    @Update("UPDATE user_body_info SET nike_name = #{nikeName},num = #{num} WHERE id = #{id}")
    int updateTransactionTest(@Param("nikeName") String nikeName, @Param("num") Integer num,@Param("id")  Integer id);
}
