package com.example.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PageRequestDTO;
import com.example.book.dto.PageResultDTO;
import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/rest")
@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<BookDTO, Book>> getMethodName(PageRequestDTO requestDTO) {
        log.info("list 요청 {}", requestDTO);
        PageResultDTO<BookDTO, Book> result = bookService.getList(requestDTO);
        return new ResponseEntity<PageResultDTO<BookDTO, Book>>(result, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<String> postCreate(BookDTO dto) {
        log.info("도서 등록 요청 {}", dto);

        Long id = bookService.create(dto);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> postModify(@PathVariable Long id, BookDTO dto) {
        log.info("도서 변경 요청 {}", dto);

        dto.setId(id);
        bookService.update(dto);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> postMethodName(@PathVariable Long id) {

        bookService.delete(id);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
