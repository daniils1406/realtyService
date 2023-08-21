package com.example.authenticationService.redis;

import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.redis.entity.RedisUser;
import com.example.authenticationService.repository.CianUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisUserServiceImpl implements RedisUserService {

    private final RedisUserRepository redisUserRepository;

    private final CianUserRepository cianUserRepository;

    private final JwtBlackListService blackListService;

    @Override
    public void addTokenToUser(CianUserEntity user, String accessToken) {
        String redisId=user.getRedisId();

        RedisUser redisUser;
        if(redisId!=null){
            redisUser= redisUserRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if(redisUser.getTokens()==null){
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(accessToken);
        }else{
            redisUser=RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(accessToken))
                    .build();
        }
        redisUserRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        cianUserRepository.setRedisId(user.getId(),user.getRedisId());
    }

    @Override
    public void addAllTokensOfUserToBlackList(CianUserEntity user) {
        if(user.getRedisId()!=null){
            RedisUser redisUser= redisUserRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens=redisUser.getTokens();
            for(String token:tokens){
                blackListService.add(token);
            }
            redisUser.getTokens().clear();
            redisUserRepository.save(redisUser);
        }
    }
}
