package com.example.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.MovieImageDTO;
import com.example.movie.dto.PageRequestDTO;
import com.example.movie.dto.PageResultDTO;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {

    // 영화목록 (페이지 나누기 + 검색)
    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 영화 등록
    Long register(MovieDTO mDto);

    // 영화 수정
    Long modify(MovieDTO mDto);

    // 영화 삭제
    void delete(Long mno);

    // 영화 상세 조회
    MovieDTO get(Long mno);

    default MovieDTO entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double reviewAvg) {
        // movie -> movieDTO
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                // .movieImageDTOs(movieImages)
                .reviewCnt(reviewCnt)
                .reviewAvg(reviewAvg)
                .regDate(movie.getRegDate())
                .updateDate(movie.getUpdateDate())
                .build();

        // MovieImage => MovieImageDto 변경 후 리스트 작업
        List<MovieImageDTO> movieImageDTOs = movieImages.stream().map(movieImage -> {
            return MovieImageDTO.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());

        movieDTO.setMovieImageDTOs(movieImageDTOs);

        return movieDTO;
    }

    default Map<String, Object[]> dtoToEntity(MovieDTO mDto) {
        return null;
    }

}
