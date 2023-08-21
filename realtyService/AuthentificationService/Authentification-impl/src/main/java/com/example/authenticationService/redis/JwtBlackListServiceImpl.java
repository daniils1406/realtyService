package com.example.authenticationService.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtBlackListServiceImpl implements JwtBlackListService {

    private final BlackListRepository blackListRepository;

    @Override
    public void add(String token) {
        blackListRepository.save(token);
    }

    @Override
    public boolean exist(String jwt) {
        return blackListRepository.exist(jwt);
    }
}
