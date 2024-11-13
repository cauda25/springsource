package com.example.book.service;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PageRequestDTO;
import com.example.book.dto.PageResultDTO;
import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepsitory;
import com.example.book.repository.CategoryRepsitory;
import com.example.book.repository.PublisherRepsitory;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepsitory bookRepsitory;
    private final CategoryRepsitory categoryRepsitory;
    private final PublisherRepsitory publisherRepsitory;

    @Override
    public Long create(BookDTO dto) {
        return bookRepsitory.save(dtoToEntity(dto)).getId();
    }

    @Override
    public BookDTO getRow(Long id) {
        return entityToDto(bookRepsitory.findById(id).get());
    }

    @Override
    public PageResultDTO<BookDTO, Book> getList(PageRequestDTO requestDTO) {
        // 페이지 나누기 개념 없을 시
        // List<Book> result = bookRepsitory.findAll();
        // return result.stream().map(entity ->
        // entityToDto(entity)).collect(Collectors.toList());

        // 페이지 나누기 개념 추가
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Book> result = bookRepsitory
                .findAll(bookRepsitory.makePredicate(requestDTO.getType(), requestDTO.getKeyword()), pageable);

        Function<Book, BookDTO> fn = (en -> entityToDto(en));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long update(BookDTO dto) {
        Book book = bookRepsitory.findById(dto.getId()).get();
        book.setPrice(dto.getPrice());
        book.setSalePrice(dto.getSalePrice());
        return bookRepsitory.save(book).getId();
    }

    @Override
    public void delete(Long id) {
        bookRepsitory.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getCateList() {
        List<Category> result = categoryRepsitory.findAll();

        return result.stream().map(entity -> cEntityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<PublisherDTO> getPubList() {
        List<Publisher> result = publisherRepsitory.findAll();

        return result.stream().map(entity -> pEntityToDto(entity)).collect(Collectors.toList());
    }

}
