package com.example.autocodetemplate.config;

import com.example.autocodetemplate.domain.UserCreateMessage;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@Slf4j
@Import({com.example.autocodetemplate.rabbit.queueBean.class})
public class RabbitConfiguration {

    // 若要缓存连接，请将 cacheMode 设置为 cacheMode.connection
    // SingleConnectionFactory  只能在框架的单元测试代码中使用 它不缓存通道
    @Bean
    public ConnectionFactory connectionFactory() {
        // CachingConnectionFactory，默认情况下，它建立一个可由应用程序共享的单个连接代理
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 支持对这些通道进行缓存，并根据通道是否是事务性的维护它们的独立缓存。
        //  可以缓存的通道数,
        // 如果您看到许多通道正在创建和关闭，则应该考虑进一步增加缓存大小
        connectionFactory.setChannelCacheSize(25);
        //connectionLimit 限制允许的连接总数 达到了限制，则使用 channelcheckouttimelmit 等待连接空闲 超过时间，将抛出 AmqpTimeoutException
        connectionFactory.setConnectionLimit(30);
        // 属性大于零时，channelCacheSize 将成为可在连接上创建的通道数的限制
        connectionFactory.setChannelCheckoutTimeout(30);
        // 连接到集群 底层连接工厂将按顺序尝试连接到每个主机
//        connectionFactory.setAddresses("host1:5672,host2:5672,host3:5672");
        // 随机连接集群每个主机
        connectionFactory.setShuffleAddresses(true);
        // 生产者 消息发布确认
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);


        return connectionFactory;
    }

//    发布是异步的ーー如何检测成功与失败
//    两个失败场景 1发布到交换机，但没有匹配的目标队列。  2发布到一个不存在的交换器。
//    前者Publisher 返回包含，如 Publisher Confirms 和 return 中所述
//    后者删除消息并且不生成返回值，可通过CachingConnectionFactory 注册一个 ChannelListener 来获取这类事件的通知

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        // 等待时间 等待消息 小于零 无限期地阻塞(或者至少在到代理的连接丢失之前)
        template.setMessageConverter(messageConverter());
        template.setReceiveTimeout(5000);
        template.setRetryTemplate(retryTemplate);
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println(message.getBody() + "：" + message.getMessageProperties().toString() + "：" + i + "："
                        + s + "：" + s1 + "：" + s2);
            }
        });
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("rabbit producer confirm ack!" + correlationData + "：" + b + "：" + s + "：");
            }
        });

        return template;
    }


    /**
     * 当接收一批消息时，解批通常由容器执行，侦听器每次调用一条消息
//     * @param configurer
//     * @param connectionFactory
     * @return
     */
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitConcurrencyListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//
//        factory.setConnectionFactory(connectionFactory());
//        factory.setMaxConcurrentConsumers(3);
//        factory.setDeBatchingEnabled(true);
//        return factory;
//    }

    @RabbitListener(queues = "myqueue")
    public void listen(@Payload UserCreateMessage message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception {
        if (message == null || message.getUserId() == null || message.getUserType() == null) {
            log.error("param can not null!");
            return;
        }
        log.info("这里是接收者1答应消息： ");
        if(message.getUserId().equals(9527)) {
            channel.basicNack(deliveryTag, false, true);
            System.out.print("消费者Nack ");
        }else if(message.equals(110)){
            channel.basicReject(deliveryTag,false);
            System.out.print("消费者Ack ");
        }else {
            channel.basicAck(deliveryTag,false);
            System.out.print("消费者Ack ");
        }
    }
}
