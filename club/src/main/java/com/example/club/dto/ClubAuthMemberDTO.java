package com.example.club.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// UserDetails <--- User <---- ClubAuthMemberDTO

@ToString
@Getter
@Setter
public class ClubAuthMemberDTO extends User {

    private String email;
    private String name;
    private boolean fromSocial;

    // security 에서는 username = id
    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
