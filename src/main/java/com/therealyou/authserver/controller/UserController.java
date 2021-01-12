package com.therealyou.authserver.controller;

import com.therealyou.authserver.dto.IdentityDto;
import com.therealyou.authserver.repo.IdentityRepo;
import com.therealyou.authserver.security.model.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest")
@Slf4j
public class UserController {
    private final IdentityRepo identityRepo;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(IdentityRepo identityRepo, UserDetailsService userDetailsService) {
        this.identityRepo = identityRepo;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    @GetMapping("checkIdentity")
    public ResponseEntity<Set<IdentityDto>> findAllIdentities(Authentication authentication){
        Set<IdentityDto> all = identityRepo
                .findAll()
                .stream()
                .map(IdentityDto::new)
                .collect(Collectors.toSet());
        log.info(authentication.toString());
        System.out.println(authentication);
        return ResponseEntity.status(200).body(all);
    }

    @Transactional
    @GetMapping("checkUsers")
    public ResponseEntity<UserDetails> findUserProfiles(@RequestParam String name){
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        return ResponseEntity.status(200).body(userDetails);
    }
}
