package com.redis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redis.model.User;
import com.redis.repository.RedisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    private final RedisRepository rdsRepo;

    public RedisController(RedisRepository redisRepository) {
        this.rdsRepo = redisRepository;
    }

    @PostMapping("/users")
    public void set(@RequestBody User value) throws JsonProcessingException {
        rdsRepo.set(value.getEmail(), value);
    }

    @GetMapping("/users/{key}")
    public ResponseEntity<User> get(@PathVariable String key) throws JsonProcessingException {
        User user = rdsRepo.get(key, User.class);
        return ResponseEntity.ok(user);
    }
}
