package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            Todo todo = Todo.builder()
                    .title("Title Test..." + i)
                    .build();
            System.out.println(todoRepository.save(todo));
        });
    }

    @Test
    public void selecrtAllTest() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void selecrtOneTest() {
        System.out.println(todoRepository.findById(5L).get());
    }

    @Test
    public void updateTest() {
        //
        Todo todo = todoRepository.findById(5L).get();
        todo.setCompleted(true);
        todo.setImportant(true);
        todoRepository.save(todo);
    }

    @Test
    public void deleteTest() {
        todoRepository.deleteById(10L);
    }

    @Test
    public void completedTest() {
        // 완료된 todo
        todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
        // 미완료 todo
        todoRepository.findByCompleted(false).forEach(todo -> System.out.println(todo));

    }
}
