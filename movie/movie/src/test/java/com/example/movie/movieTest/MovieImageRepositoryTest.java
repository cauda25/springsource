package com.example.movie.movieTest;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

@SpringBootTest
public class MovieImageRepositoryTest {

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void testTotalListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageRequest);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            Movie movie = (Movie) objects[0];
            MovieImage movieImage = (MovieImage) objects[1];
            Long cnt = (Long) objects[2];
            Double avg = (Double) objects[3];

            System.out.println(movie);
            System.out.println(movieImage);
            System.out.println(cnt);
            System.out.println(avg);
        }
    }
}
