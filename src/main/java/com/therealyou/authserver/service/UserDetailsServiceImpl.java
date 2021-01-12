package com.therealyou.authserver.service;

import com.therealyou.authserver.entity.Identity;
import com.therealyou.authserver.repo.IdentityRepo;
import com.therealyou.authserver.security.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IdentityRepo identityRepo;

    @Autowired
    public UserDetailsServiceImpl(IdentityRepo identityRepo) {
        this.identityRepo = identityRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Identity identity = identityRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with with username does note exist (%s)", username)));
        return new UserProfile(identity);
    }
}
