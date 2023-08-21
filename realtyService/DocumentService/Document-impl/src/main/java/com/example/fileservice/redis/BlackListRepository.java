package com.example.fileservice.redis;

public interface BlackListRepository {
    void save(String token);

    boolean exist(String token);
}
