package com.example.board.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.board.entity.Member;
import com.example.board.entity.Reply;

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
    private String content;
    private String title;

    // private Member member;
    private String writerEmail;
    private String writername;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 개수
    private Long replyCnt;
}
