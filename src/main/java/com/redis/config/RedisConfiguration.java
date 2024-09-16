package com.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> StringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setValueSerializer(StringRedisSerializer);
        redisTemplate.setKeySerializer(StringRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(50));
        RedisCacheConfiguration userListsCache =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5));
        return RedisCacheManager.builder(connectionFactory).withCacheConfiguration("userLists", userListsCache)
                .cacheDefaults(defaultConfig)
                .build();
    }

}
