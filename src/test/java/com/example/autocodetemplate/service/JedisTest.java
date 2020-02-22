package com.example.autocodetemplate.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JedisTest {

    /**
     * Jedis不是线程安全的，一般通过JedisPool获取Jedis实例
     *
     * Jedis不支持读写分离，Lettuce内置支持读写分离：1只读主 只读从  2优先读主 优先读从
     */
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    private final String DB = "jedis-db";

    @Test
    public void testQueryByCache() {
        log.info(jedisPoolConfig.toString());

        // 注意要释放资源
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(DB, "nike football", "88.1");

            Map<String, String> goods = jedis.hgetAll(DB);

            String price = jedis.hget(DB, "nike football");
        }
    }


}
