package com.example.advertismentService.security.utils.impl;

import advertismentImpl.model.jooq.schema.enums.Role;
import advertismentImpl.model.jooq.schema.tables.pojos.CianUserEntity;
import advertismentImpl.security.details.UserDetailsImpl;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.advertismentService.security.entity.ParsedToken;
import com.example.advertismentService.security.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
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

        System.out.println(jwt.getClaims().toString());

        return ParsedToken.builder()
                .id(id)
                .role(role)
                .login(login)
                .build();

    }
}
