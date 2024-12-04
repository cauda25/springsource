package com.example.movie.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDTO;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public List<ReviewDTO> getList(@PathVariable Long mno) {
        log.info("리뷰 리스트 요청{}", mno);

        List<ReviewDTO> reviews = reviewService.getReviews(mno);
        return reviews;
    }

    @PreAuthorize("authentication.name == #email")
    @DeleteMapping("/{mno}/{reviewNo}")
    public Long getMethodName(@PathVariable Long mno, @PathVariable Long reviewNo, String email) {
        log.info("리뷰 삭제 요청{}", reviewNo);
        reviewService.removeReview(reviewNo);

        return reviewNo;

    }

    @GetMapping("/{mno}/{reviewNo}")
    public ReviewDTO getList(@PathVariable Long mno, @PathVariable Long reviewNo) {
        log.info("리뷰 리스트 요청{}", reviewNo);

        ReviewDTO reviewDTO = reviewService.getReview(reviewNo);
        return reviewDTO;
    }

    @PreAuthorize("authentication.name == #reviewDTO.email")
    @PutMapping("/{mno}/{reviewNo}")
    public Long putMethodName(@PathVariable Long mno, @PathVariable Long reviewNo, @RequestBody ReviewDTO reviewDTO) {
        log.info("리뷰 수정 요청{} ,{}", reviewNo, reviewDTO);

        reviewDTO.setReviewNo(reviewNo);
        reviewNo = reviewService.modifyReview(reviewDTO);
        return reviewNo;
    }

    @PostMapping("/{mno}")
    public Long postMethodName(@RequestBody ReviewDTO reviewDTO) {
        log.info("리뷰 등록 요청{} ", reviewDTO);

        return reviewService.addReview(reviewDTO);
    }

}
