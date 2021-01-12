package com.therealyou.authserver.dto;

import com.therealyou.authserver.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String role;
    private Set<AuthorityDto> authorities;

    public RoleDto(Role role){
        this.id = role.getId();
        this.role = role.getRole();
        this.authorities = role
                .getAuthorities()
                .stream().map(AuthorityDto::new)
                .collect(Collectors.toSet());
    }
}
