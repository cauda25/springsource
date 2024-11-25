package com.example.movie.repository.total;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.QMovie;
import com.example.movie.entity.QMovieImage;
import com.example.movie.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MovieImageReviewRepositoryImpl extends QuerydslRepositorySupport implements MovieImageReviewRepository {

    public MovieImageReviewRepositoryImpl() {
        super(MovieImage.class);
    }

    @Override
    public Page<Object[]> getTotalList(String type, String keyword, Pageable pageable) {
        QMovieImage qMovieImage = QMovieImage.movieImage;
        QReview qReview = QReview.review;
        QMovie qMovie = QMovie.movie;

        JPQLQuery<MovieImage> query = from(qMovieImage).leftJoin(qMovie).on(qMovie.eq(qMovieImage.movie));

        JPQLQuery<Long> rCnt = JPAExpressions.select(qReview.countDistinct()).from(qReview)
                .where(qReview.movie.eq(qMovieImage.movie));
        JPQLQuery<Double> rAvg = JPAExpressions.select(qReview.grade.avg().round()).from(qReview)
                .where(qReview.movie.eq(qMovieImage.movie));

        JPQLQuery<Long> inum = JPAExpressions.select(qMovieImage.inum.max()).from(qMovieImage)
                .groupBy(qMovieImage.movie);

        JPQLQuery<Tuple> tuple = query.select(qMovie, qMovieImage, rCnt, rAvg)
                .where(qMovieImage.inum.in(inum));

        // bno >0 조건
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMovie.mno.gt(0L));

        // 검색

        // Sort
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            // Sort 기준 컬럼명 가져오기
            String prop = order.getProperty();

            // order를 어는 엔티티에 작용할 것인지
            PathBuilder<Movie> orderByExpressoin = new PathBuilder<>(Movie.class, "movie");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpressoin.get(prop)));
        });

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        long count = tuple.fetchCount();

        return new PageImpl<>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }

    @Override
    public List<Object[]> getMovieRow(Long mno) {
        return null;
    }

}
