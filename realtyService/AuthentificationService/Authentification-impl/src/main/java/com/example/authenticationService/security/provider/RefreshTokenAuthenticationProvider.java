package com.example.authenticationService.security.provider;

import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.repository.RefreshTokenRepository;
import com.example.authenticationService.security.authentication.RefreshTokenAuthentication;
import com.example.authenticationService.security.details.UserDetailsImpl;
import com.example.authenticationService.security.exceptions.RefreshTokenException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

    private final RefreshTokenRepository tokenRepository;

    private final CianUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String refreshTokenValue = (String) authentication.getCredentials();
        CianUserEntity user = userRepository.findById(tokenRepository.findById(UUID.fromString(refreshTokenValue)).getUserId(), Usertype.CLIENT);
        try {
            UserDetails userDetails = new UserDetailsImpl(user);
            return new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString())));
        } catch (JWTVerificationException e) {
            throw new RefreshTokenException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RefreshTokenAuthentication.class.isAssignableFrom(authentication);
    }
}
