package com.example.projact1.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SampleDTO {
    private Long id;
    private String first;
    private String last;
    private LocalDateTime regDateTime;
}
