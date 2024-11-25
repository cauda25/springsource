package com.example.board.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.board.dto.MemberDTO;
import com.example.board.dto.MemberOAthDTO;
import com.example.board.entity.Member;
import com.example.board.entity.role.MemberRole;
import com.example.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardUserDetilsService implements UserDetailsService, BoardUserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login {}", username);

        // username 으로 사용자 찾기
        Optional<Member> result = memberRepository.findById(username);
        // 없다면 UsernameNotFoundException
        if (!result.isPresent())
            throw new UsernameNotFoundException("이메일을 확인해 주세요");

        // 있다면 MemberDto * 인증된 값을 담아서 리턴
        Member member = result.get();
        MemberDTO mDto = entityToDto(member);

        return new MemberOAthDTO(mDto);
    }

    @Override
    public String register(MemberDTO dto) throws IllegalStateException {

        // 중복 이메일 검사
        validateDuplicationMember(dto.getEmail());

        // 비밀번호 암호화
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        // 권한 부여
        dto.setRole(MemberRole.MEMBER);
        // 저장
        Member member = memberRepository.save(dtoToEntity(dto));
        // 이름 반환
        return member.getName();
    }

    // 중복 이메일 검사
    private void validateDuplicationMember(String email) throws IllegalStateException {
        Optional<Member> result = memberRepository.findById(email);

        if (result.isPresent()) {
            // 강제 exeption발생
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

    }

}