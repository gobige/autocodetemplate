package com.example.autocodetemplate.service;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

    /**
     * JDBC 数据库操作流程：
     *
     * 1. DriverManager 通过 静态方法 借助Java SPI机制 加载Driver 配置类并初始化
     * 2. Driver类加载并初始化时，如Mysql的驱动类，会在 【静态域中调用 DriverManager的 registerDriver 方法把自己 存储到registeredDrivers集合中】
     * 3. DriverManager 通过getConnection方法，首先 遍历 registeredDrivers集合和传入URL继续匹配，立即返回
     * 4. 不同的Driver进行connect实现，返回connection对象
     *
     * 【线程上下文类加载器】 JDK的Driver接口在rt.jar中由启动类加载器进行加载，而三方具体Driver实现在ClassPath目录下，所以需要打破【双亲委派原则】
     *
     * JDBC 实现，因为JDBC实现依靠Java SPI机制，而在ServiceLoader 用 load方法时获取当前线程 contextClassLoader，
     * 所以JDBC只需将 Application ClassLoader设置到线程 contextClassLoader即可
     */
    @Test
    public void jdbcLoadTest() {

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb");

            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from user_body_info");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
            }
        } catch (Exception e) {

        }finally {

        }
    }
}
