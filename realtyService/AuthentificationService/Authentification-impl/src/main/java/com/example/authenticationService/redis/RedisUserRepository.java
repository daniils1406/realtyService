package com.example.authenticationService.redis;

import com.example.authenticationService.redis.entity.RedisUser;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {

}
