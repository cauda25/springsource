package com.example.guestbook.repositorytest;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.example.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookApplicationTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insert() {
        LongStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .writer("Writer" + i)
                    .title("Title" + i)
                    .content("Content... " + i)
                    .build();

            guestBookRepository.save(guestBook);
        });
    }

    @Test
    public void update() {
        GuestBook guestbook = guestBookRepository.findById(300L).get();
        guestbook.setWriter("히가시노 게이고");
        guestbook.setTitle("나미야 잡화가게의 기적");
        guestbook.setContent(
                "2012년 12월 19일 국내에 번역 출간된 이래 10년 연속 역대 최장기 베스트셀러 소설의 자리를 지키며, 2017년 지난 10년간 가장 많이 판매된 소설 1위, 2018년 국내 누적 판매 100만 부, 2019년 2010년대 베스트셀러 2위(소설 분야 1위), 2020년 초판 100쇄 돌파, 2022년 현재 170만 독자의 선택이라는 대기록을 세운 “21세기 가장 경이로운 베스트셀러”(교보문고), 한국인이 가장 사랑하는 일본 작가 히가시노 게이고의 아주 특별한 대표작 『나미야 잡화점의 기적』이 국내 출간 10주년을 맞아 무선 보급판으로 발간된다.\r\n"
                        + //
                        "위로와 공감을 얻을 수 있는 따뜻한 공간을 그린 초판 표지의 감동을 그대로 담아낸 무선판은 다소 무게감 있었던 양장판과 다르게 누구나 가볍게 들고 다니며 읽을 수 있도록 했다. 또 10주년을 기념해 표지를 바탕으로 제작한 모바일 메신저 테마를 배포해 독자들의 성원에 보답할 예정이다.");
        guestBookRepository.save(guestbook);
    }

    @Test
    public void testSearch() {
        // 제목에 1 이라는 글자가 들어있는 게시물 조회
        QGuestBook qGuestBook = QGuestBook.guestBook;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qGuestBook.title.contains("1"));
        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(i -> System.out.println(i));
    }

    @Test
    public void testSearch2() {
        // 제목 혹은 내용에 1 이라는 글자가 들어있고 gno > 0

        QGuestBook qGuestBook = QGuestBook.guestBook;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTitle = qGuestBook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestBook.content.contains(keyword);
        builder.and(expressionTitle.or(expressionContent));
        builder.and(qGuestBook.gno.gt(0L));

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(i -> System.out.println(i));
    }

    @Test
    public void testSearch3() {
        // 제목 혹은 내용에 1 이라는 글자가 들어있고 gno > 0

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        // Predicate predicate(BooleanBuilder 사용), Pageable pageable
        Page<GuestBook> result = guestBookRepository.findAll(guestBookRepository.makePredicate("tc", "나미야"),
                pageable);

        result.stream().forEach(i -> System.out.println(i));
    }

    @Test
    public void delete() {
        guestBookRepository.deleteById(256L);
    }
}
