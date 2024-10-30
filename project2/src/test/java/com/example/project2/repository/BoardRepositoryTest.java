package com.example.project2.repository;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entify.Board;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insert() {
        LongStream.rangeClosed(1, 20).forEach(i -> {

            Board board = Board.builder()
                    .content("Test Content... " + i)
                    .title("Test Title... " + i)
                    .writer("Test Writer... " + i)
                    .build();
            System.out.println(boardRepository.save(board));
        });
    }

    @Test
    public void read() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void update() {
        Board board = boardRepository.findById(5L).get();
    }

    @Test
    void delete() {
        boardRepository.deleteById(5l);
    }

}
