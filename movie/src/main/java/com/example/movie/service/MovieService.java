package com.example.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
                .reviewAvg(reviewAvg != null ? reviewAvg : 0.0d)
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

    default Map<String, Object> dtoToEntity(MovieDTO mDto) {
        Map<String, Object> resultMap = new HashMap<>();
        Movie movie = Movie.builder()
                .mno(mDto.getMno())
                .title(mDto.getTitle())
                .build();
        resultMap.put("movie", movie);
        List<MovieImageDTO> movieImageDTOs = mDto.getMovieImageDTOs();
        // MovieImageDto => MovieImage 변경 후 MovieImage List 형태로 작성
        // List<MovieImage> movieImages = new ArrayList<>();
        // if (movieImageDTOs != null && movieImageDTOs.size() > 0) {
        // movieImageDTOs.forEach(dto -> {
        // MovieImage movieImage = MovieImage.builder()
        // .uuid(dto.getUuid())
        // .imgName(dto.getImgName())
        // .path(dto.getPath())
        // .movie(movie)
        // .build();
        // movieImages.add(movieImage);
        // });
        // }

        if (movieImageDTOs != null && movieImageDTOs.size() > 0) {
            List<MovieImage> movieImages = movieImageDTOs.stream().map(dto -> {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(dto.getUuid())
                        .imgName(dto.getImgName())
                        .path(dto.getPath())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());

            resultMap.put("movieImages", movieImages);

        }
        return resultMap;
    }

}
