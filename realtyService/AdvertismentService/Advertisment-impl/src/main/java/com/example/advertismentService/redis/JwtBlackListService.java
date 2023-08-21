package com.example.advertismentService.redis;


public interface JwtBlackListService {
    void add(String token);

    boolean exist(String jwt);
}
