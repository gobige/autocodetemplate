package com.example.autocodetemplate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WstBusinessServiceImplTest {
    @Resource
    private TestService testService;

    @Resource
    private JavaMailSender mailSender;

    private Future<String> doTaskTwo() throws Exception {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始第二个任务");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("第二个任务完成");
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return new AsyncResult<>("任务2完成,耗时:" + (endTime - startTime));
    }

    @Test
    public void testGetById() throws Exception {
        Long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                     doTaskTwo();
                } catch (Exception e) {
                    System.out.println("throw exception!!!!");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                     doTaskTwo();
                } catch (Exception e) {
                    System.out.println("throw exception!!!!");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    doTaskTwo();
                } catch (Exception e) {
                    System.out.println("throw exception!!!!");
                }
            }
        }).start();

        Thread.sleep(7000);
    }

    @Test
    public void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("569484515@qq.com");
        message.setTo("394157821@qq.com");
        message.setSubject("test");
        message.setText("testtest");

        mailSender.send(message);
    }

}
