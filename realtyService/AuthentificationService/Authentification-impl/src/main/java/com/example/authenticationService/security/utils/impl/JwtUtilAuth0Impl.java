package com.example.authenticationService.security.utils.impl;

import com.example.authenticationService.model.jooq.schema.enums.Role;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.security.details.UserDetailsImpl;
import com.example.authenticationService.security.entity.ParsedToken;
import com.example.authenticationService.security.utils.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class JwtUtilAuth0Impl implements JwtUtil {

    @Value("${jwt.access_token_expires_time_minutes}")
    private long ACCESS_TOKEN_EXPIRES_TIME;

    private final JwtEncoder jwtEncoder;

    public final JwtDecoder jwtDecoder;

    public JwtUtilAuth0Impl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        super();
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Map<String, String> generateTokens(String id, String subject, String authority, String issuer) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(ACCESS_TOKEN_EXPIRES_TIME, ChronoUnit.MINUTES))
                .subject(subject)
                .claim("id", id)
                .claim("role", authority)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();


        String refreshToken = UUID.randomUUID().toString();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    @Override
    public Authentication buildAuthentication(String token) throws JWTVerificationException {
        ParsedToken parsedToken = parse(token);

        CianUserEntity user = new CianUserEntity();
        user.setLogin(parsedToken.getLogin());
        user.setRole(Role.valueOf(parsedToken.getRole()));
        UserDetails userDetails = new UserDetailsImpl(user);

        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                Collections.singleton(new SimpleGrantedAuthority(parsedToken.getRole())));
    }

    public ParsedToken parse(String token) throws JWTVerificationException {
        String login = null;
        String role = null;
        String id = null;
        Jwt jwt = jwtDecoder.decode(token);
        role = jwt.getClaims().get("role").toString();
        login = jwt.getClaims().get("sub").toString();
        id = jwt.getClaims().get("id").toString();


        return ParsedToken.builder()
                .id(id)
                .role(role)
                .login(login)
                .build();

    }
}
