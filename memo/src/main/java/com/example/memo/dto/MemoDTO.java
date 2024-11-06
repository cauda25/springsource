package com.example.memo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {

    private Long memo;

    @NotBlank(message = "내용 입력")
    private String memoText;

    private LocalDateTime createDateTime;
    private LocalDateTime lastModifyDateTime;

}
