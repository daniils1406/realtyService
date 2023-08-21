package com.example.advertismentService.redis;

public interface BlackListRepository {
    void save(String token);

    boolean exist(String token);
}
