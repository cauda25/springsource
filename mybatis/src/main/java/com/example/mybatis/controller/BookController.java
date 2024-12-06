package com.example.mybatis.controller;

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

import com.example.mybatis.dto.BookDTO;
import com.example.mybatis.dto.CategoryDTO;
import com.example.mybatis.dto.PageRequestDTO;
import com.example.mybatis.dto.PageResultDTO;
import com.example.mybatis.dto.PublisherDTO;

import com.example.mybatis.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public void getMethodName(@ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model) {
        log.info("list 요청 {}", requestDTO);
        List<BookDTO> result = bookService.getList(requestDTO);
        int total = bookService.getTotalCnt(requestDTO);

        log.info("list {}", result);
        log.info("total {}", total);

        model.addAttribute("result", new PageResultDTO<>(requestDTO, total, result));

    }

    @GetMapping(value = { "/read", "/modify" })
    public void getRead(Long id, @ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model) {
        log.info("도서 조회 요청");
        BookDTO dto = bookService.getRow(id);
        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String postModify(BookDTO dto, @ModelAttribute(name = "rqdto") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {
        log.info("도서 변경 요청 {}", dto);
        log.info("requestDTO {}", requestDTO);
        if (bookService.update(dto)) {
            rttr.addAttribute("id", dto.getId());
            rttr.addAttribute("page", requestDTO.getPage());
            rttr.addAttribute("size", requestDTO.getSize());
            rttr.addAttribute("type", requestDTO.getType());
            rttr.addAttribute("keyword", requestDTO.getKeyword());
            return "redirect:read";

        } else {
            return "/book/modify";
        }

    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long id, @ModelAttribute(name = "rqdto") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {
        log.info("도서 삭제 요청 {}", id);
        log.info("requestDTO {}", requestDTO);
        bookService.delete(id);

        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:list";
    }

    @GetMapping("/create")
    public void getcCreate(@ModelAttribute("dto") BookDTO dto,
            @ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model) {
        log.info("도서 등록 폼 요청");

        List<CategoryDTO> c = bookService.getCateList();
        List<PublisherDTO> p = bookService.getPubList();

        model.addAttribute("cDTO", c);
        model.addAttribute("pDTO", p);
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BookDTO dto, BindingResult result,
            @ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model,
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

        rttr.addFlashAttribute("msg", "도서가 등록 되었습니다.");
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:list";
    }

}
