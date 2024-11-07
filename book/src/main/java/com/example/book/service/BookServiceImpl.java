package com.example.book.service;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
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
    public List<BookDTO> getList() {
        List<Book> result = bookRepsitory.findAll();

        return result.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
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
