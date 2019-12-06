package com.example.autocodetemplate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Slf4j
@Configuration
public class DataSourceCofiguration {

//    @Bean
 // 这里配置多数据源，要去(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,     JdbcTemplateAutoConfiguration.class})
//    public DataSourceProperties testdbDataSourceProperties() {
//        DataSourceProperties dataSourceProperties = new DataSourceProperties();
//        dataSourceProperties.setUrl("jdbc:h2:mem:testdb");
//        dataSourceProperties.setInitialize(true);
//        dataSourceProperties.setPassword("");
//        dataSourceProperties.setUsername("sa");
//        // 初始化脚本文件的编码
//        dataSourceProperties.setSqlScriptEncoding(Charset.defaultCharset());
//
//        return dataSourceProperties;
//    }
//
//    @Bean
//    @Primary
//    public DataSource testdbDataSource() {
//        DataSourceProperties dataSourceProperties = testdbDataSourceProperties();
//        log.info("testdb datasource: {}", dataSourceProperties.getUrl());
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }
//
//    @Bean
//    @Resource
//    public PlatformTransactionManager testdbTxManager(DataSource testdbDataSource) {
//        return new DataSourceTransactionManager(testdbDataSource);
//    }
//
//    @Bean
//    public DataSourceProperties testMysqldbDataSourceProperties() {
//        DataSourceProperties dataSourceProperties = new DataSourceProperties();
//        dataSourceProperties.setUrl("jdbc:mysql://mysql.dev.car-house.cn:3306/car_house");
//        dataSourceProperties.setPassword("acxw-dbPwd~1@3");
//        dataSourceProperties.setUsername("carhouse");
//
//        return dataSourceProperties;
//    }
//
//    @Bean
//    public DataSource testMysqldbDataSource() {
//        DataSourceProperties dataSourceProperties = testMysqldbDataSourceProperties();
//        log.info("testMysqldb datasource: {}", dataSourceProperties.getUrl());
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }
//
//    @Bean
//    @Resource
//    public PlatformTransactionManager testMysqldbTxManager(DataSource testMysqldbDataSource) {
//        return new DataSourceTransactionManager(testMysqldbDataSource);
//    }


}
