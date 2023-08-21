package com.example.chatService.redis;


public interface JwtBlackListService {
    void add(String token);

    boolean exist(String jwt);
}
