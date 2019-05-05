package com.example.autocodetemplate.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * spring 缓存配置
 */

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // 自定义缓存实现  ConcurrentHashMap实现
        cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("cacheDB1")));

        return cacheManager;
    }
}
