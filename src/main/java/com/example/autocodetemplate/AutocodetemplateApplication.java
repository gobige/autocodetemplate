package com.example.autocodetemplate;

import com.example.autocodetemplate.dao.SysAppVersionInfoDao;
import com.example.autocodetemplate.domain.SysAppVersionInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;

/**
 * TempgeleratorApplication.java不仅是启动引导类，还是配置类
 *
 * @SpringBootApplication          ←---开启组件扫描和自动配置
 * @SpringBootApplication将三个有用的注解组合在了一起。
 * @Configuration：标明该类使用Spring基于Java的配置。
 * @EnableScheduling 使用定时任务
 * @EnableAsync 运行异步方法 @Async所修饰的函数不要定义为static类型,不然不会生效
 * @ComponentScan：启用组件扫描@EnableAutoConfiguration：也可以称为@Abracadabra2，开启了Spring Boot自动配置的魔力，
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
public class AutocodetemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocodetemplateApplication.class, args);
        String resource="mybatis-config.xml";
        InputStream inputStream = null;
        try {
             inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory=null;
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=null;
        try {
            sqlSession=sqlSessionFactory.openSession();
            SysAppVersionInfoDao sysAppVersionInfoDao = sqlSession.getMapper(SysAppVersionInfoDao.class);
            SysAppVersionInfo role = sysAppVersionInfoDao.queryByIdBySelectProvider(16);
            System.out.println(role.getId()+":"+role.getRemark()+":"+role.getAppVersion());
            sqlSession.commit();

        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
