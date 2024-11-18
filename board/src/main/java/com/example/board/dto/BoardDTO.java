package com.example.board.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.board.entity.Member;
import com.example.board.entity.Reply;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

    private Long bno;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    // private Member member;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String writerEmail;
    private String writername;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 개수
    private Long replyCnt;
}
