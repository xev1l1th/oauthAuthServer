package com.therealyou.authserver.repo;

import com.therealyou.authserver.entity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityRepo extends JpaRepository<Identity, Long> {
    Optional<Identity> findByUsername(String username);
}
