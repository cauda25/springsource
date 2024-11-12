package com.example.book.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// http://localhost:8080/book/list?page=2&size=20&type=c$keyword=소년
@Builder
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PageRequestDTO {
    private int size;
    private int page;

    // 검색
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

}
