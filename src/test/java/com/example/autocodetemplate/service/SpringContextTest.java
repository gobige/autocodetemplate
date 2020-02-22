package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Actor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * springContext 包含业务信息和配置信息
 * 托管依赖注入的bean
 *
 * BeanFactory 接口
 * ApplicationContext  接口
 * WebApplicationContext  接口
 *
 *
 * web上下文
 * DispatcherServlet
 * Servlet WebApplicationContext   DispatcherServlet加载
 * Root WebApplicationContext  ContextLoaderListener加载
 *
 * AnnotationConfigApplicationContext FileSystemXmlApplicationContext ClassPathXmlApplicationContext
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextTest {

    @Autowired
    private SpringCacheTestService springCacheTestService;

    @Test
    public void testQueryByCache() {

        Actor actor = springCacheTestService.queryByCache(1);
        Assert.assertNotNull(actor);
    }

}
