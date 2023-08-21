package com.example.advertismentService.security.details;

import advertismentImpl.model.jooq.schema.enums.Status;
import advertismentImpl.model.jooq.schema.tables.pojos.CianUserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final CianUserEntity user;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleAuthority = user.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleAuthority);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(Status.VERIFIED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getStatus().equals(Status.BANNED);
    }
}
