package com.example.todo.service;

import java.util.List;

import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;

public interface TodoService {
    List<TodoDTO> geList(Boolean completed);

    TodoDTO getRow(Long id);

    TodoDTO create(TodoDTO dto);

    List<TodoDTO> getCompletedList();

    Long updateCompleted(TodoDTO dto);

    void deldeteRow(Long id);

    // dto ==> entity
    public default Todo dtoToEntity(TodoDTO dto) {
        return Todo.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .completed(dto.isCompleted())
                .important(dto.isImportant())
                .build();
    }

    // entitu ==> dto
    public default TodoDTO entityToDto(Todo entity) {
        return TodoDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .completed(entity.isCompleted())
                .important(entity.isImportant())
                .build();
    }
}
