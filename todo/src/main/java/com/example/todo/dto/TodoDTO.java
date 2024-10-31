package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {

    private Long id;
    private boolean completed;
    private boolean important;
    private String title;

}
