package com.example.project2.repository;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.project2.entify.Board;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insert() {
        LongStream.rangeClosed(1, 300).forEach(i -> {

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
        Board board = boardRepository.findById(6L).get();
        board.setTitle("ddd");
        boardRepository.save(board);
    }

    @Test
    void delete() {
        boardRepository.deleteById(5l);
    }

    @Test
    public void testTitleList() {
        // boardRepository.findByTitle("Test Title... 1").forEach(i ->
        // System.out.println(i));
        // boardRepository.findByTitleLike("Test Title...").forEach(i ->
        // System.out.println(i));
        // boardRepository.findByTitleStartingWith("Title").forEach(i ->
        // System.out.println(i));
        // boardRepository.findByTitleEndingWith("1").forEach(i ->
        // System.out.println(i));
        // boardRepository.findByTitleContaining("Test").forEach(i ->
        // System.out.println(i));
        boardRepository.findByWriterContainingOrTitleContaining("Writer",
                "Title").forEach(i -> System.out.println(i));
        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 20L).forEach(i
        // -> System.out.println(i));
        // boardRepository.findByIdGreaterThanOrderByIdDesc(20L).forEach(i ->
        // System.out.println(i));

        // 0 : 1 page 의미, pageSize : 한 페이지에 보여질 게시물 개수
        // Pageable pageable = PageRequest.of(1, 10);
        // boardRepository.findByIdGreaterThanOrderByIdDesc(0L, pageable).forEach(i ->
        // System.out.println(i));

        // boardRepository
        // .findByWriterList("Writer")
        // .forEach(i -> System.out.println(i));
    }

}
