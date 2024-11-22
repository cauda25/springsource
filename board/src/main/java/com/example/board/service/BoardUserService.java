package com.example.board.service;

import com.example.board.dto.MemberDTO;
import com.example.board.entity.Member;

public interface BoardUserService {
    // 회원가입
    String register(MemberDTO dto) throws IllegalStateException;

    // dto => entity
    public default Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
        return member;
    }

    // entity => dto
    public default MemberDTO entityToDto(Member entity) {
        MemberDTO dto = MemberDTO.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
        return dto;
    }
}
