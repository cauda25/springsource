package com.example.movie.service;

import java.util.List;

import com.example.movie.dto.ReviewDTO;
import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

public interface ReviewService {
    // movie 번호를 이용해 특정 영화의 모든 리뷰 조회
    List<ReviewDTO> getReviews(Long mno);

    // 특정 리뷰 조회
    ReviewDTO getReview(Long reviewNo);

    Long addReview(ReviewDTO reviewDTO);

    Long modifyReview(ReviewDTO reviewDTO);

    void removeReview(Long reviewDTO);

    default ReviewDTO entityToDto(Review review) {

        return ReviewDTO.builder()
                .reviewNo(review.getReviewNo())
                .grade(review.getGrade())
                .text(review.getText())
                .mno(review.getMovie().getMno())
                .mmid(review.getMember().getMmid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getNickname())
                .regDate(review.getRegDate())
                .updateDate(review.getUpdateDate())
                .build();
    }

    default Review dtoToEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .reviewNo(reviewDTO.getReviewNo())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .member(Member.builder().mmid(reviewDTO.getMmid()).build())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .build();

    }
}
