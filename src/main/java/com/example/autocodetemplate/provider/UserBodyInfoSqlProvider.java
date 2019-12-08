package com.example.autocodetemplate.provider;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class UserBodyInfoSqlProvider {
    public String queryById() {
        return "SELECT * FROM user_body_info WHERE id = #{id}";
    }

}
