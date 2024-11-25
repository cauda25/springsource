package com.example.movie.movieTest;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository mImageRepository;

    @Test
    public void testMovieInsert() {
        LongStream.range(1, 50).forEach(i -> {
            Movie movie = Movie.builder().title("영화제목" + i).build();
            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .imgName("test" + j + ".jpg")
                        .movie(movie)
                        .build();
                mImageRepository.save(movieImage);
            }
        });
    }

    @Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

}
