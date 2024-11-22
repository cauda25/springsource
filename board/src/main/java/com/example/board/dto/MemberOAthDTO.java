package com.example.board.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Builder
@Setter
@ToString
@Getter
public class MemberOAthDTO extends User {

    private MemberDTO mDto;

    // private List<String> list = list.of("spring","java","sdk");

    public MemberOAthDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MemberOAthDTO(MemberDTO mDto) {

        this(mDto.getEmail(), mDto.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + mDto.getRole().toString())));
        this.mDto = mDto;
    }

}
