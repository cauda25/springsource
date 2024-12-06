package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDTO;
import com.example.mybatis.dto.PageResultDTO;
import com.example.mybatis.dto.PublisherDTO;

public interface BookService {

    boolean create(BookDTO dto);

    BookDTO getRow(Long id);

    List<BookDTO> getList(PageRequestDTO requestDTO);

    int getTotalCnt(PageRequestDTO requestDTO);

    boolean update(BookDTO dto);

    boolean delete(Long id);

    List<CategoryDTO> getCateList();

    List<PublisherDTO> getPubList();

}
