package com.example.guestbook.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GuestBookDTO {
    private Long gno;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
