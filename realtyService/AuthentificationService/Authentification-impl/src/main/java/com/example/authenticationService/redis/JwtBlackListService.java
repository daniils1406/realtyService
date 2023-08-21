package com.example.authenticationService.redis;


public interface JwtBlackListService {
    void add(String token);

    boolean exist(String jwt);
}
