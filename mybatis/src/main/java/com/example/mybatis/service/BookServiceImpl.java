package com.example.mybatis.service;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDTO;
import com.example.mybatis.dto.PageResultDTO;
import com.example.mybatis.dto.PublisherDTO;
import com.example.mybatis.mapper.BookMapper;

import groovy.transform.Final;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    @Override
    public Long create(BookDTO dto) {
        return null;
    }

    @Override
    public BookDTO getRow(Long id) {
        return null;
    }

    @Override
    public List<BookDTO> getList(PageRequestDTO requestDTO) {
        return bookMapper.listAll(requestDTO);
    }

    @Override
    public Long update(BookDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<CategoryDTO> getCateList() {
        return null;
    }

    @Override
    public List<PublisherDTO> getPubList() {
        return null;
    }

    @Override
    public int getTotalCnt(PageRequestDTO requestDTO) {
        return bookMapper.totalCnt(requestDTO);
    }

}
