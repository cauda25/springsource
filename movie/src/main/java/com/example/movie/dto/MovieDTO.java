package com.example.movie.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NotFound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
    private Long mno;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Builder.Default
    private List<MovieImageDTO> movieImageDTOs = new ArrayList<>();

    // 영화 평점 평균
    private Double reviewAvg;
    // 영화 평점 개수
    private Long reviewCnt;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
