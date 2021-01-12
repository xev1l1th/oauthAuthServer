package com.therealyou.authserver.dto;

import com.therealyou.authserver.entity.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorityDto {
    private Long id;
    private String authority;

    public AuthorityDto(Authority authority){
        this.id = authority.getId();
        this.authority = authority.getAuthority();
    }
}
