package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Apple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
public class WstBusinessServiceImplTest {
    @Resource
    private TestService testService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private JavaMailSender mailSender;

    @Test
    public void testGetById() throws Exception{
        Long startTime = System.currentTimeMillis();
        Future<String> task1 = testService.doTaskOne();
        Future<String> task2 = testService.doTaskTwo();
        Future<String> task3 = testService.doTaskThree();
        Long endTime = System.currentTimeMillis();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
        }
        System.out.println("任务1" + task1.get());
        System.out.println("任务2" + task2.get());
        System.out.println("任务3" + task3.get());
        System.out.println("所有任务完成耗时:" + (endTime - startTime));
    }

    @Test
    public void testGetSql() throws Exception{
        testService.testGetSql();
    }

    @Test
    public void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("569484515@qq.com");
        message.setTo("394157821@qq.com");
        message.setSubject("test");
        message.setText("testtest");

        mailSender.send(message);
    }

    @Test
    public void testRedis() {
        testRedisTemplate();
    }

    private void testRedisTemplate() {
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
}
