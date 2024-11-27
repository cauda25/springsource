package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.constant.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
    private Long mmid;

    private String email;

    private String password;

    private String nickname;

    private MemberRole memberRole;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
