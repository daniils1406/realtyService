package com.example.authenticationService.redis;

public interface BlackListRepository {
    void save(String token);

    boolean exist(String token);
}
