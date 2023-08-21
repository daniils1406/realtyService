package com.example.fileservice.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BlackListRepositoryImpl implements BlackListRepository {

    private final RedisTemplate<String,String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token,"");
    }

    @Override
    public boolean exist(String token) {
        Boolean hasKey=redisTemplate.hasKey(token);
        return hasKey != null && hasKey;
    }
}
