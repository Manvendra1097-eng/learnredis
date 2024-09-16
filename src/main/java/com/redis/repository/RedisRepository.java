package com.redis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private final ValueOperations<String, Object> valueOperations;
    private final ObjectMapper mapper = new ObjectMapper();

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public <T> T get(String key, Class<T> clazz) throws JsonProcessingException {
        Object o = valueOperations.get(key);
        if (o == null) return null;
        return mapper.readValue(o.toString(), clazz);
    }

    public void set(String key, Object value) throws JsonProcessingException {
        valueOperations.set(key, mapper.writeValueAsString(value));
    }
}
