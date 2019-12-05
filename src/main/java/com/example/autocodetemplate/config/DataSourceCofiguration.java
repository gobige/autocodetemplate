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

    @Bean
//    @ConfigurationProperties("foo.datasource")// 这里配置多数据源，要去application处extend DataSourceAutoConfiguration  DataSourceTransactionManagerAutoConfiguration  JdbcTemplateAutoConfiguration
    public DataSourceProperties testdbDataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl("jdbc:h2:mem:testdb");
        dataSourceProperties.setInitialize(true);
        dataSourceProperties.setPassword("");
        dataSourceProperties.setUsername("sa");
        // 初始化脚本文件的编码
        dataSourceProperties.setSqlScriptEncoding(Charset.defaultCharset());

        return dataSourceProperties;
    }

    @Bean
    @Primary
    public DataSource testdbDataSource() {
        DataSourceProperties dataSourceProperties = testdbDataSourceProperties();
        log.info("testdb datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager testdbTxManager(DataSource testdbDataSource) {
        return new DataSourceTransactionManager(testdbDataSource);
    }
//
//    @Bean
////    @ConfigurationProperties("foo.datasource")
//    public DataSourceProperties test2dbDataSourceProperties() {
//        DataSourceProperties dataSourceProperties = new DataSourceProperties();
//        dataSourceProperties.setUrl("jdbc:h2:mem:testdb2");
//        dataSourceProperties.setPassword("");
//        dataSourceProperties.setUsername("sa");
//        dataSourceProperties.setSchema(Arrays.asList("classpath:schema.sql"));
//        dataSourceProperties.setData(Arrays.asList("classpath:data.sql"));
//
//        return dataSourceProperties;
//    }
//
//    @Bean
//    public DataSource test2dbDataSource() {
//        DataSourceProperties dataSourceProperties = test2dbDataSourceProperties();
//        log.info("testdb datasource: {}", dataSourceProperties.getUrl());
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }
//
//    @Bean
//    @Resource
//    public PlatformTransactionManager test2dbTxManager(DataSource test2dbDataSource) {
//        return new DataSourceTransactionManager(test2dbDataSource);
//    }

}
