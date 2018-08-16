package com.example.autocodetemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * TempgeleratorApplication.java不仅是启动引导类，还是配置类
 *
 * @SpringBootApplication          ←---开启组件扫描和自动配置
 * @SpringBootApplication将三个有用的注解组合在了一起。
 * @Configuration：标明该类使用Spring基于Java的配置。
 * @ComponentScan：启用组件扫描@EnableAutoConfiguration：也可以称为@Abracadabra2，开启了Spring Boot自动配置的魔力，
 */
@SpringBootApplication
@EnableCaching
public class AutocodetemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocodetemplateApplication.class, args);
    }
}
