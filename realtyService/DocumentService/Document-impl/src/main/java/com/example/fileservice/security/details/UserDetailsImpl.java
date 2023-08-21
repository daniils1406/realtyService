package com.example.fileservice.security.details;


import com.example.fileservice.entity.CianUserEntity;
import com.example.fileservice.entity.Status;
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
        String roleAuthority = String.valueOf(user.getRole());
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
