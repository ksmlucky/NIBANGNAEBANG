package com.footprints.authservice.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<Long, String> redisTemplate;

    public void setValues(Long key, String data) {
        ValueOperations<Long, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValues(Long key, String data, Duration duration) {
        ValueOperations<Long, String> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    public String getValue(Long key) {
        ValueOperations<Long, String> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    public void deleteValues(Long key) {
        redisTemplate.delete(key);
    }

}
