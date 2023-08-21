package com.example.authenticationService.redis;

import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;

public interface RedisUserService {
    void addTokenToUser(CianUserEntity byLogin, String accessToken);

    void addAllTokensOfUserToBlackList(CianUserEntity user);
}
