package com.example.autocodetemplate.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;


public class KafkaProducerTest {
    public static void main(String[] args) {
        testProducerSend();
    }
     public static void testProducerSend()  {
        Properties props = new Properties();
        // 通常配置一个就够了，会自动从集群元数据获取其他存活代理消息
        props.put("bootstrap.servers", "47.107.136.101:8080");
        // 提交时保证所有response都得到应答，虽然慢但是可靠
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 线程安全，单实例，
        Producer<String, String> producer = new KafkaProducer<>(props);
         for (int i = 0; i < 10; i++) {
             System.out.println("start send record " + i);

             // 异步，立即返回，同类消息批量收集发送
             // 如果失败会自动重试，但是也会导致重复的问题
             // 为每个分区维护未发送记录的缓冲区
             producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
         }

         // 暂用后台I/O资源提高发送效率，不关闭会造成内存泄露
        producer.close();
    }

     public static void testStream()  {
         Properties props = new Properties();
         props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
         props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.136.101:8080");
         props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
         props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

         StreamsBuilder builder = new StreamsBuilder();
         builder.<String, String>stream("my-input-topic").mapValues(value -> String.valueOf(value.length())).to("my-output-topic");

         KafkaStreams streams = new KafkaStreams(builder.build(), props);
         streams.start();

     }

}
