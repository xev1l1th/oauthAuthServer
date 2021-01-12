package com.therealyou.authserver.dto;

import com.therealyou.authserver.entity.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class IdentityDto {
    private Long id;
    private Set<RoleDto> roles;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Long age;

    public IdentityDto(Identity identity){
        this.id = identity.getId();
        this.roles = identity
                .getRoles()
                .stream()
                .map(RoleDto::new)
                .collect(Collectors.toSet());
        this.username = identity.getUsername();
        this.password = identity.getPassword();
        this.firstName = identity.getFirstName();
        this.lastName = identity.getLastName();
        this.age = identity.getAge();
    }
}
