package com.example.chatService.security.utils.impl;


import com.example.chatService.entity.CianUserEntity;
import com.example.chatService.entity.Role;
import com.example.chatService.security.details.UserDetailsImpl;
import com.example.chatService.security.entity.ParsedToken;
import com.example.chatService.security.utils.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtUtilAuth0Impl implements JwtUtil {


    public final JwtDecoder jwtDecoder;

    public JwtUtilAuth0Impl(JwtDecoder jwtDecoder) {
        super();
        this.jwtDecoder = jwtDecoder;
    }


    @Override
    public Authentication buildAuthentication(String token) throws JWTVerificationException {
        ParsedToken parsedToken = parse(token);

        CianUserEntity user=new CianUserEntity();
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
        String id=null;
        Jwt jwt = jwtDecoder.decode(token);
        System.out.println(jwt.getClaims().get("role").toString());
        role = jwt.getClaims().get("role").toString();
        login = jwt.getClaims().get("sub").toString();
        id=jwt.getClaims().get("id").toString();


        return ParsedToken.builder()
                .id(id)
                .role(role)
                .login(login)
                .build();

    }
}
