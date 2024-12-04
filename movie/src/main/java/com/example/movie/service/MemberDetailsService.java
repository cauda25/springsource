package com.example.movie.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.dto.AuthMemberDTO;
import com.example.movie.dto.MemberDTO;
import com.example.movie.dto.PasswordDTO;
import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemberDetailsService implements UserDetailsService, MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Service username {}", username);

        Optional<Member> result = memberRepository.findByEmail(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("이메일 확인");
        }

        // 이메일이 존재한다면 entity -> dto 변경
        Member member = result.get();

        MemberDTO memberDTO = MemberDTO.builder()
                .mmid(member.getMmid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .memberRole(member.getMemberRole())
                .build();
        return new AuthMemberDTO(memberDTO);
    }

    @Transactional
    @Override
    public void nicknameUpdate(MemberDTO memberDTO) {

        memberRepository.updateNickname(memberDTO.getNickname(), memberDTO.getEmail());
    }

    @Override
    public void passwordUpdate(PasswordDTO passwordDTO) throws Exception {
        // email 을 이용해 사용자 찾기
        // Optional<Member> result =
        // memberRepository.findByEmail(passwordDTO.getEmail());
        // if (!result.isPresent())
        // throw new UsernameNotFoundException("이메일 확인");
        Member member = memberRepository.findByEmail(passwordDTO.getEmail()).get();

        // 현재 비밀번호(db에 저장된 값)가 입력 비밀번호(입력값)와 일치하는지 검증
        if (!passwordEncoder.matches(passwordDTO.getCurrentPassword(), member.getPassword())) {
            // false : 되돌려 보내기
            throw new Exception("현재 비밀번호를 확인");
        } else {
            // true : 새 비밀번호로 수정
            member.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            memberRepository.save(member);
        }
    }

    @Transactional
    @Override
    public void leave(PasswordDTO passwordDTO) throws Exception {

        Member member = memberRepository.findByEmail(passwordDTO.getEmail()).get();

        if (!passwordEncoder.matches(passwordDTO.getCurrentPassword(), member.getPassword())) {
            // false : 되돌려 보내기
            throw new Exception("현재 비밀번호를 확인");
        }

        // 리뷰 삭제 (리뷰를 작성한 멤버를 이용해서 삭제)
        reviewRepository.deleteByMember(member);
        // 회원 삭제
        memberRepository.deleteById(member.getMmid());
    }

    @Override
    public String register(MemberDTO memberDTO) {

        Member member = dtoToEntity(memberDTO);
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return memberRepository.save(member).getNickname();

    }

}
