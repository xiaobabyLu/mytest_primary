package com.xiaobaby.myproject.redis.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Lu Yufeng
 * @date 2018/7/30 下午5:32
 */

@Configuration
public class RedisConfig {

    @Value("${redis.address}")
    private String redisAddress;

    @Value("${redis.max.active}")
    private int redisMaxActive;

    @Value("${redis.max.idle}")
    private int redisMaxIdle;



    @Bean(name = "anyCache")
    public Cache getLocalCache() {
        return CacheBuilder.newBuilder()
                //设置写缓存后8小时过期
                .expireAfterWrite(8, TimeUnit.HOURS)
                //设置缓存容器的初始容量为35
                .initialCapacity(35)
                //设置缓存最大容量为10000，超过之后按照LRU最近最少使用算法来移除缓存项
                .maximumSize(10000)
                //设置要统计缓存的命中率
                .recordStats()
                .build();
    }


}
