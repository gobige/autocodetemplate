package com.example.autocodetemplate.dao;

import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.filter.ActorFilter;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ActorDao {

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "testResultRemark",column = "remark")
    })
    @Select(" SELECT * FROM actor WHERE create_time BETWEEN #{filter.queryStartCreateTime} AND #{filter.queryEndCreateTime} " +
            "AND user_Type = #(filter.userType)")
    Actor queryByActorFilter(@Param("filter") ActorFilter filter);

}