package com.example.club.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.club.dto.ClubAuthMemberDTO;
import com.example.club.entity.ClubMember;
import com.example.club.entity.constant.ClubRole;
import com.example.club.repository.ClubMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuthDetilsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequsetTest {}", userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info("{} : {}", k, v);

        });
        ClubMember clubMember = savaSociaClubMember(oAuth2User.getAttribute("email"));

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                clubMember.getEmail(), clubMember.getPassword(), clubMember.isFromSocial(),
                clubMember.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes());

        return clubAuthMemberDTO;

    }

    // 소셜로그인 정보를 이용한 회원가입
    private ClubMember savaSociaClubMember(String email) {

        // 소셜 로그인 계정으로 가입한 회원 존재 여부 확인
        Optional<ClubMember> result = clubMemberRepository.findByEmailAndFromSocial(email, false);

        if (result.isPresent()) {
            return result.get();
        }

        // 소셜 로그인 계정을 가입한 회원이 없는 경우
        ClubMember clubMember = ClubMember.builder()
                .email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        clubMember.addMemberRole(ClubRole.USER);
        clubMemberRepository.save(clubMember);

        return clubMember;
    }

}
