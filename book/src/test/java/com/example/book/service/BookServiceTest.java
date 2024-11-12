package com.example.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.dto.BookDTO;
import com.example.book.dto.PageRequestDTO;
import com.example.book.dto.PageResultDTO;
import com.example.book.entity.Book;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Transactional
    @Test
    public void testList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(12)
                .size(10)
                .build();
        PageResultDTO<BookDTO, Book> resultDTO = bookService.getList(requestDTO);
        resultDTO.getDtoList().forEach(dto -> System.out.println(dto));
        System.out.println("요청 페이지 " + resultDTO.getPage());
        System.out.println("목록 개수 " + resultDTO.getSize());
        System.out.println("시작 페이지 " + resultDTO.getStart());
        System.out.println("끝 페이지 " + resultDTO.getEnd());
        System.out.println("PageList " + resultDTO.getPageList());
        System.out.println("이전 페이지 여부 " + resultDTO.isPrev());
        System.out.println("다음 페이지 여부 " + resultDTO.isNext());
        System.out.println("전체 페이지 " + resultDTO.getTotalPage());
    }
}
