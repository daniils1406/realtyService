package com.example.fileservice.redis;


public interface JwtBlackListService {
    void add(String token);

    boolean exist(String jwt);
}
