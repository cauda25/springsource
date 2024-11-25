package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDTO {
    private Long reviewNo;

    private int grade;

    private String text;

    // movie의 mno 담기
    private Long mno;

    // member mid,nickname,email
    private Long mmid;
    private String email;
    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
