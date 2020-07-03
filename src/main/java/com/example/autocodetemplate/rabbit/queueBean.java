package com.example.autocodetemplate.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;

public class queueBean {

    @Bean
    public TopicExchange userExchange(RabbitAdmin rabbitAdmin) {
        TopicExchange userExchange = new TopicExchange(Exchange.USER_TOPIC_EXCHANGE.getKey());
        userExchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return userExchange;
    }

    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

    @Bean
    public Binding bindingMyQueue(Queue myQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(myQueue).to(userExchange).with(RoutingKey.USER_CREATE_SUCCESS.getKey());
    }
}
