package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Apple;
import com.example.autocodetemplate.util.DistributedLocker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    /**
     * 支持客户端：  Jedis Lettuce
     *
     * Spring 对 redis的支持，
     * RedisTemplate（一定要设置过期时间）,RedisRepository
     *
     * 定义spring cache 配置Redis缓存方式 :
     * spring.cache.type=redis
     * spring.cache.cache-names=spring-cache
     * spring.cache.redis.time-to-live=3000
     * spring.cache.redis.cache-null-values=false
     * spring.redis.host=localhost
     *
     * Redis Sentinel 是Redis的一种高可用方案，
     * 作用：监控，通知，自动故障转移，服务发现
     *
     * JedisSentinelPool
     *
     * Redis Cluster  Redis 集群模式
     * 作用：数据自动分片，在部分节点失效时有一定可用性
     *
     * JedisCluster
     *
     *
     */


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        testRedisTemplate();
    }

    @Autowired
    private DistributedLocker distributedLocker;

    private void testRedisTemplate() {
        // 获取 匹配的 key
        Set<String> keys = redisTemplate.keys("set" + "*");

        redisTemplate.delete(keys);

        // 插入某个元素
        redisTemplate.opsForValue().set("name", "yates", 10, TimeUnit.SECONDS);
        // 获取某个元素的值
        System.out.println(redisTemplate.opsForValue().get("name"));
        // 获取某个元素的值，然后更新
        System.out.println(redisTemplate.opsForValue().getAndSet("name", "yangchao"));
        // 如果某个元素不存在则增加
        System.out.println(redisTemplate.opsForValue().setIfAbsent("name", "yangchao"));
        Map<String, String> maps = new HashMap<>();
        maps.put("name1", "name1");
        maps.put("name2", "name2");
        maps.put("name3", "name3");
        maps.put("name4", "name4");
        // 批量增加
        redisTemplate.opsForValue().multiSet(maps);
        // 批量增加如果不存在
        redisTemplate.opsForValue().multiSetIfAbsent(maps);
        // 自增
        redisTemplate.opsForValue().increment("number1", 1);
        // 找到某个元素尾部添加
        redisTemplate.opsForValue().append("name", " is man");
        // 获取字符串byte数
        System.out.println(redisTemplate.opsForValue().size("name"));
        System.out.println("/////////////////////////////////////////");

        // list
        Apple apple1 = new Apple(1, 100, "brazi");
        Apple apple2 = new Apple(2, 111, "japen");
        Apple apple3 = new Apple(3, 222, "china");
        redisTemplate.opsForList().leftPush("apples", apple1);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple2);
        apples.add(apple3);
        // 批量左插
        redisTemplate.opsForList().leftPushAll("apples", apples);
        // 集合若存在该元素插入
        redisTemplate.opsForList().leftPushIfPresent("apples", apple1);
        // 集合最左插入
        redisTemplate.opsForList().leftPush("apples", apple1, apple2);
        // 集合最右插入
        redisTemplate.opsForList().rightPush("apples", apple3);
        // 获取集合数量
        System.out.println(redisTemplate.opsForList().size("apples"));

        redisTemplate.opsForList().rightPush("strs", "str1");
        redisTemplate.opsForList().rightPush("strs", "str2");
        redisTemplate.opsForList().rightPush("strs", "str4");
        redisTemplate.opsForList().rightPush("strs", "str3");
        redisTemplate.opsForList().rightPush("strs", "str4");
        redisTemplate.opsForList().rightPush("strs", "str5");
        // 指定位置元素更新
        redisTemplate.opsForList().set("strs", 3, "str6");
        // 指定位置元素
        System.out.println(redisTemplate.opsForList().index("strs", 2));
        // k集合最左移除
        System.out.println(redisTemplate.opsForList().leftPop("strs"));
        // k集合最左移除，若不存在等待阻塞指定时间
        System.out.println(redisTemplate.opsForList().leftPop("strs", 3, TimeUnit.SECONDS));
        // k集合最右移除
        System.out.println(redisTemplate.opsForList().rightPop("strs"));
        // k集合最右移除，若不存在等待阻塞指定时间
        System.out.println(redisTemplate.opsForList().rightPop("strs", 3, TimeUnit.SECONDS));
        // k集合最右移到新k1集合
        System.out.println(redisTemplate.opsForList().rightPopAndLeftPush("strs", "leftInStr"));
        // 删除元素 1>从头开始第一个元素 <-1 从尾到头第一个元素  0 所有元素
        redisTemplate.opsForList().remove("strs", 1, "str4");

        System.out.println(redisTemplate.opsForList().range("strs", 0, 1));
        // 截取某一段位置存储更新
        redisTemplate.opsForList().trim("strs", 1, -1);
        System.out.println("/////////////////////////////////////////");

        // HASH
        redisTemplate.opsForHash().put("hashs", "hash1", "hash1val");
        // 若不存在存储
        redisTemplate.opsForHash().putIfAbsent("hashs", "hash2", "hash2val");
        Map map = new HashMap();
        map.put("hash3", "hash3val");
        map.put("hash4", "hash4val");
//        map.put("hashnum",4);
        // 批量增加
        redisTemplate.opsForHash().putAll("hashs", map);
        // 删除指定元素
        redisTemplate.opsForHash().delete("hashs", "hash1");
        // 所有元素
        redisTemplate.opsForHash().entries("hashs");
        // 是否存在key
        System.out.println(redisTemplate.opsForHash().hasKey("hashs", "hash2"));
        // 获取某个元素
        System.out.println(redisTemplate.opsForHash().get("hashs", "hash23"));
//        System.out.println(redisTemplate.opsForHash().increment("hashs","hashnum",2.2));
        // 所有元素key
        System.out.println(redisTemplate.opsForHash().keys("hashs"));
        // 所有元素的value
        System.out.println(redisTemplate.opsForHash().values("hashs"));
        // 元素数量
        System.out.println(redisTemplate.opsForHash().size("hashs"));

        // 迭代器
        Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("hashs", ScanOptions.NONE);

        // SET
        System.out.println("/////////////////////////////////////////");
        redisTemplate.opsForSet().add("sets", "set1", "set2", "set3");
        redisTemplate.opsForSet().add("sets2", "set11", "set22", "set3");
        // 求交集
        System.out.println(redisTemplate.opsForSet().intersect("sets2", "sets"));
        // 交集存储
        System.out.println(redisTemplate.opsForSet().intersectAndStore("sets2", "sets", "setIntersects"));
        // 并集
        System.out.println(redisTemplate.opsForSet().union("sets2", "sets"));
        // 并集存储
        System.out.println(redisTemplate.opsForSet().unionAndStore("sets2", "sets", "setUnions"));
        // sets2中没有在sets中的差集
        System.out.println(redisTemplate.opsForSet().difference("sets2", "sets"));
        // 差集 存储
        System.out.println(redisTemplate.opsForSet().differenceAndStore("sets2", "sets", "setDiffs"));
        // 是否存在
        System.out.println(redisTemplate.opsForSet().isMember("sets", "set9"));
        // 随机移除元素
        System.out.println(redisTemplate.opsForSet().pop("sets"));
        // 移除指定元素
        System.out.println(redisTemplate.opsForSet().remove("sets", "set1", "set2"));
        // 获取元素数量
        System.out.println(redisTemplate.opsForSet().size("sets"));
        // 获取所有元素
        System.out.println(redisTemplate.opsForSet().members("sets"));
        // 随机一个元素
        System.out.println(redisTemplate.opsForSet().randomMember("sets"));
        // 随机 可能会重复选择
        System.out.println(redisTemplate.opsForSet().randomMembers("sets", 2));
        redisTemplate.opsForSet().size("sets");

        Cursor<Map.Entry<Object, Object>> curosrSet = redisTemplate.opsForSet().scan("sets", ScanOptions.NONE);

        System.out.println("/////////////////////////////////////////");

        // zset
        redisTemplate.opsForZSet().add("zset", "zset-1", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zset-2", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zset-3", 9.6);
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        // 批量增加
        System.out.println(redisTemplate.opsForZSet().add("zset", tuples));
        // 获取元素数量
        System.out.println(redisTemplate.opsForZSet().size("zset"));
        // 获取指定元素分数
        System.out.println(redisTemplate.opsForZSet().score("zset", "zset-2"));
        // 移除指定元素
        System.out.println(redisTemplate.opsForZSet().remove("zset-2", "zset-1"));
        // 自增
        System.out.println(redisTemplate.opsForZSet().incrementScore("zset", "zset-1", 1.2));
        // 从小到大排序所在位置
        System.out.println(redisTemplate.opsForZSet().rank("zset", "zset-1"));
        // 从大到小排序所在位置
        System.out.println(redisTemplate.opsForZSet().reverseRank("zset", "zset-1"));
        // 位置在某区间内元素
        System.out.println(redisTemplate.opsForZSet().range("zset", 0, -1));
        // 分数在某区间的元素
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset", 0, 1));
        // 分数在某区间的元素个数
        System.out.println(redisTemplate.opsForZSet().count("zset", 0, 1));
        // 移除位置在某区间内元素
        System.out.println(redisTemplate.opsForZSet().removeRange("zset", 0, 1));
        // 移除分数在某区间的元素
        System.out.println(redisTemplate.opsForZSet().removeRangeByScore("zset", 0, 1));
        // 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
        System.out.println(redisTemplate.opsForZSet().unionAndStore("zset", "zset", "unionzset"));
        // 计算给定的一个有序集的交集，并存储在新的 destKey中，key相同的话会把score值相加
        System.out.println(redisTemplate.opsForZSet().intersectAndStore("zset", "zset", "intersectzset"));

    }

    @Test
    public void testBatchTmplate() {
//        final String key = "Mall:Index:Page:Items104";
//        System.out.println(redisTemplate.opsForList().leftPop(key));


//        System.out.println(redisTemplate.opsForList().range(key, 0, listOperations.size(key)));

        // redis muti 开启事务 执行redis指令，请求到达不执行，最后exec一起执行，保持原子性
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {

//                redisOperations.watch("name");
                redisTemplate.multi();
                redisTemplate.opsForValue().set("name","yates");
//                redisTemplate.opsForValue().set("name2",1/0);
                redisTemplate.opsForValue().set("name3","yates3");
                redisTemplate.opsForValue().set("name4","yates4");

                redisTemplate.exec();
                return null;
            }
        });

        System.out.println(redisTemplate.opsForValue().get("name"));

        // pipelined 一起发送请求，节省网络带宽
        List<Object> pipelines = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                redisOperations.opsForValue().get("name");
                redisOperations.opsForValue().get("name2");
                redisOperations.opsForValue().get("name3");
                redisOperations.opsForValue().get("name4");

                return null;
            }
        });

        for (Object pipeline : pipelines) {
            System.out.println(String.valueOf(pipeline));
        }
    }

    @Test
    public void testRedLock() throws Exception {
        //连接redis
        distributedLocker.lock("index_page_lock", TimeUnit.MINUTES, 10);

        Thread.sleep(5000L);
        distributedLocker.unlock("index_page_lock");
    }
}
