package com.example.club.service;

import com.example.club.dto.ClubMemberDTO;

public interface ClubService {
    // 회원가입
    String register(ClubMemberDTO dto);
}
