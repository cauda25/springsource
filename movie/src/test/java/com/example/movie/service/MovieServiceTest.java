package com.example.movie.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.PageRequestDTO;
import com.example.movie.dto.PageResultDTO;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void testGet() {
        MovieDTO movieDTO = movieService.get(4L);
        System.out.println(movieDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("안녕")
                .build();
        PageResultDTO<MovieDTO, Object[]> result = movieService.getList(requestDTO);
        System.out.println(result.getDtoList());

    }
}
