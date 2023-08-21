package com.example.chatService.redis;

public interface BlackListRepository {
    void save(String token);

    boolean exist(String token);
}
