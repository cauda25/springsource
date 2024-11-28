package com.example.movie.movieTest;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.Review;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReviewRepositorytest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

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

    // @Transactional
    @Test
    public void test() {
        Movie movie = movieRepository.findById(18L).get();

        List<Review> list = reviewRepository.findByMovie(movie);
        // System.out.println(list);
        list.forEach(review -> {
            System.out.println(review.getText());
            System.out.println(review.getGrade());
            System.out.println(review.getMember().getNickname());
        });
    }

}
