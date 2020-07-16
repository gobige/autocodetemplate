package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.UserCreateMessage;
import com.example.autocodetemplate.rabbit.Exchange;
import com.example.autocodetemplate.rabbit.RoutingKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitMQTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMq() throws Exception {
        rabbitTemplate.convertAndSend(Exchange.USER_TOPIC_EXCHANGE.getKey(), RoutingKey.USER_CREATE_SUCCESS.getKey(), new UserCreateMessage(9527, 1));
    }

    @Test
    public void testReceiveMq() throws Exception {
        UserCreateMessage object = (UserCreateMessage)rabbitTemplate.receiveAndConvert("myqueue");

        System.out.println(object);
    }
}
