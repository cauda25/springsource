package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDTO;
import com.example.book.dto.CategoryDTO;
import com.example.book.dto.PublisherDTO;
import com.example.book.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public void getMethodName(Model model) {
        log.info("list 요청");
        List<BookDTO> list = bookService.getList();
        model.addAttribute("list", list);
    }

    @GetMapping(value = { "/read", "/modify" })
    public void getRead(Long id, Model model) {
        log.info("도서 조회 요청");
        BookDTO dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postModify(BookDTO dto, RedirectAttributes rttr) {
        log.info("도서 변경 요청 {}", dto);
        Long id = bookService.update(dto);

        rttr.addAttribute("id", id);
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long id) {
        log.info("도서 삭제 요청 {}", id);
        bookService.delete(id);

        return "redirect:list";
    }

    @GetMapping("/create")
    public void getcCreate(@ModelAttribute("dto") BookDTO dto, Model model) {
        log.info("도서 등록 폼 요청");

        List<CategoryDTO> c = bookService.getCateList();
        List<PublisherDTO> p = bookService.getPubList();

        model.addAttribute("cDTO", c);
        model.addAttribute("pDTO", p);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDTO dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 등록 요청 {}", dto);
        List<CategoryDTO> c = bookService.getCateList();
        List<PublisherDTO> p = bookService.getPubList();

        model.addAttribute("cDTO", c);
        model.addAttribute("pDTO", p);

        if (result.hasErrors()) {
            return "/book/create";
        }

        Long id = bookService.create(dto);

        rttr.addFlashAttribute("msg", id + " 번 도서가 등록 되었습니다.");

        return "redirect:list";
    }

}
