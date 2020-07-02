package com.example.autocodetemplate.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitMQTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMq() throws Exception {

        rabbitTemplate.convertAndSend("myexchange","myroutingkey","this is a message!");
    }
    @Test
    public void testReceiveMq() throws Exception {

        rabbitTemplate.receiveAndConvert("myqueue");
    }



}
