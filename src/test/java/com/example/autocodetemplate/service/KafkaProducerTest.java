package com.example.autocodetemplate.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class KafkaProducerTest {
    public static void main(String[] args) {
        testProducerSend();
    }
     public static void testProducerSend()  {
        Properties props = new Properties();
        props.put("bootstrap.servers", "47.107.136.101:8080");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
         for (int i = 0; i < 10; i++) {
             System.out.println("start send record " + i);
             producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
         }

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
