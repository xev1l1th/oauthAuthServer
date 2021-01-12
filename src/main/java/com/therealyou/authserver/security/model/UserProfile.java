package com.therealyou.authserver.security.model;

import com.therealyou.authserver.entity.Identity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Transactional
public class UserProfile implements UserDetails {

    private final Identity identity;

    public UserProfile(Identity identity) {
        this.identity = identity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return identity
                .getRoles()
                .stream()
                .flatMap(role -> role
                        .getAuthorities()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority())))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return identity.getPassword();
    }

    @Override
    public String getUsername() {
        return identity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
