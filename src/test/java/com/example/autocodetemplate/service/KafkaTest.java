package com.example.autocodetemplate.service;

import com.example.autocodetemplate.Enum.EnumLotteryQueryType;
import com.example.autocodetemplate.domain.AreaAndPostCodeResult;
import com.example.autocodetemplate.domain.OcrRecResult;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.util.FileUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    @Resource
    private LotteryQueryService lotteryQueryService;

    @Test
    public void testProducerSend()  {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();
    }

}
