package com.example.movie.movieTest;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;

@SpringBootTest
public class ReviewRepositorytest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testReviewInsert() {
        LongStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long) (Math.random() * 49) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            Long mid = (long) (Math.random() * 50) + 1;
            Member member = Member.builder().mmid(mid).build();

            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("영화에 대한...." + i)
                    .member(member)
                    .movie(movie)
                    .build();

            reviewRepository.save(review);
        });
    }
}
