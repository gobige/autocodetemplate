package com.example.autocodetemplate.service;

import com.example.autocodetemplate.rabbit.Exchange;
import com.example.autocodetemplate.rabbit.RoutingKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

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

class UserCreateMessage implements Serializable {
    private static final long serialVersionUID = -8995790645422688337L;

    public UserCreateMessage(){}

    public UserCreateMessage(Integer userId, Integer userType) {
        this.userId = userId;
        this.userType = userType;
    }

    private Integer userId;
    private Integer userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}