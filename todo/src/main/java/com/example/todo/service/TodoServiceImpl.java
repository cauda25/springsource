package com.example.todo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service // ==@Controller 객체 생성 후 스프링 컨테이너가 관리해줘
@Log4j2
public class TodoServiceImpl implements TodoService {

    // @Autowired 스프링 컨데이너가 관리하는 Bean 중 TodoRepository(객체) 주입
    private final TodoRepository todoRepository;
    // TodoRepository todoRepository = new TodoRepository();

    @Override
    public List<TodoDTO> geList(Boolean completed) {
        // List<Todo> result = todoRepository.findAll();

        // List<TodoDTO> list2 = new ArrayList<>();
        // list.forEach(entity -> {
        // list2.add(entityToDto(entity));
        // });

        // 전체 todo
        // List<TodoDTO> list = result
        // .stream()
        // .map(todo -> entityToDto(todo))
        // .collect(Collectors.toList());

        // 미완료 todo / 완료 todo
        List<Todo> result = todoRepository.findByCompleted(completed);
        List<TodoDTO> list = result.stream().map(todo -> entityToDto(todo)).collect(Collectors.toList());
        return list;
    }

    @Override
    public TodoDTO getRow(Long id) {
        Todo todo = todoRepository.findById(id).get();

        return entityToDto(todo);
    }

    @Override
    public TodoDTO create(TodoDTO dto) {
        // dto => entity
        Todo todo = dtoToEntity(dto);
        return entityToDto(todoRepository.save(todo));
    }

    @Override
    public List<TodoDTO> getCompletedList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCompletedList'");
    }

    @Override
    public Long updateCompleted(TodoDTO dto) {
        Todo todo = todoRepository.findById(dto.getId()).get();

        todo.setCompleted(dto.isCompleted());
        Todo updateTodo = todoRepository.save(todo);

        return updateTodo.getId();
    }

    @Override
    public void deldeteRow(Long id) {
        todoRepository.deleteById(id);
    }

}
