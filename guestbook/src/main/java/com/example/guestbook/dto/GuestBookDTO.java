package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "writer 은 필수 입력 요소입니다.")
    private String writer;

    @NotEmpty(message = "title 은 필수 입력 요소입니다.")
    private String title;

    @NotBlank(message = "content 은 필수 입력 요소입니다.")
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
