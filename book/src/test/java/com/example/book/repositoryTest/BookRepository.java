package com.example.book.repositoryTest;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepsitory;
import com.example.book.repository.CategoryRepsitory;
import com.example.book.repository.PublisherRepsitory;

@SpringBootTest
public class BookRepository {

    @Autowired
    private BookRepsitory bookRepository;

    @Autowired
    private CategoryRepsitory categoryRepsitory;

    @Autowired
    private PublisherRepsitory publisherRepsitory;

    @Test
    public void testCategoryName() {
        // 카테고리 목록
        categoryRepsitory.findAll().forEach(cn -> {
            System.out.println(cn);
        });

        // 출판사 목록
        publisherRepsitory.findAll().forEach(pn -> {
            System.out.println(pn);
        });
    }

    @Test
    public void CategoryIsert() {
        categoryRepsitory.save(Category.builder().name("소설").build());
        categoryRepsitory.save(Category.builder().name("건강").build());
        categoryRepsitory.save(Category.builder().name("컴퓨터").build());
        categoryRepsitory.save(Category.builder().name("여행").build());
        categoryRepsitory.save(Category.builder().name("경제").build());

    }

    @Test
    public void publisherIsert() {

        publisherRepsitory.save(Publisher.builder().name("미래의정").build());
        publisherRepsitory.save(Publisher.builder().name("웅진리빙하우스").build());
        publisherRepsitory.save(Publisher.builder().name("김영사").build());
        publisherRepsitory.save(Publisher.builder().name("길벗").build());
        publisherRepsitory.save(Publisher.builder().name("동아출판").build());

    }

    @Test
    public void bookIsert() {

        LongStream.rangeClosed(1, 10).forEach(i -> {

            long num = ((int) (Math.random() * 5) + 1);
            Book book = Book.builder()
                    .title("title " + i)
                    .writer("writer " + i)
                    .price((int) (15000 * i))
                    .salePrice((int) (15000 * i + 0.9))
                    .category(Category.builder().id(num).build())
                    .publisher(Publisher.builder().id(num).build())
                    .build();
            bookRepository.save(book);
        });
    }

    @Transactional
    @Test
    public void testList() {
        // 도서 목록 전체 조회
        bookRepository.findAll().forEach(bk -> {
            System.out.println(bk);
            System.out.println(bk.getCategory());
            System.out.println(bk.getPublisher());

        });
    }

    @Transactional
    @Test
    public void testGet() {
        // 도서 목록 전체 조회
        Book book = bookRepository.findById(5L).get();
        System.out.println(book);
        System.out.println(book.getCategory().getName());
        System.out.println(book.getPublisher().getName());
    }

    @Test
    public void update() {
        Book book = bookRepository.findById(5L).get();
        book.setPrice(32000);
        book.setSalePrice(22800);
        bookRepository.save(book);
    }

    @Test
    public void delete() {
        bookRepository.deleteById(10L);
    }
}
