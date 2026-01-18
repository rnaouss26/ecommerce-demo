package com.cnam.ecommerce.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
@RequestMapping("/api")
public class RedisTestController {

    private final StringRedisTemplate redis;

    public RedisTestController(StringRedisTemplate redis) {
        this.redis = redis;
    }

    @GetMapping("/redis-test")
    public String test() {
        redis.opsForValue().set("ping", "pong");
        return redis.opsForValue().get("ping");
    }
}
