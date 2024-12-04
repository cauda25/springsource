package com.example.movie.service;

import com.example.movie.dto.MemberDTO;
import com.example.movie.dto.PasswordDTO;
import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;

public interface MemberService {
    // 닉네임 수정
    void nicknameUpdate(MemberDTO memberDTO);

    // 비밀번호 수정
    void passwordUpdate(PasswordDTO passwordDTO) throws Exception;

    // 회원 탈퇴
    void leave(PasswordDTO passwordDTO) throws Exception;

    // 회원 가입
    String register(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .mmid(memberDTO.getMmid())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .nickname(memberDTO.getNickname())
                .memberRole(MemberRole.MEMBER)
                .build();
    }

    // default MemberDTO entityToDto(Member entity) {
    // return MemberDTO.builder()
    // .mmid(entity.getMmid())
    // .email(entity.getEmail())
    // .password(entity.getPassword())
    // .nickname(entity.getNickname())
    // .memberRole(MemberRole.MEMBER)
    // .regDate(entity.getRegDate())
    // .updateDate(entity.getUpdateDate())
    // .build();
    // }
}
