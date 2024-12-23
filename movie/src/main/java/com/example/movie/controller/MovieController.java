package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MovieDTO;
import com.example.movie.dto.PageRequestDTO;
import com.example.movie.dto.PageResultDTO;
import com.example.movie.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/movie")
@Controller
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, Model model) {
        log.info("전체 movie list 요청 {}", pageRequestDTO);

        PageResultDTO<MovieDTO, Object[]> result = movieService.getList(pageRequestDTO);

        model.addAttribute("result", result);

    }

    @GetMapping({ "/read", "/modify" })
    public void getMethodName(@RequestParam Long mno, Model model,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("영화상세 정보 요청 {}", mno);
        MovieDTO movieDTO = movieService.get(mno);

        model.addAttribute("movieDto", movieDTO);
    }

    @PostMapping("/modify")
    public String postModify(MovieDTO movieDto,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("영화 정보 수정 {}", movieDto);

        Long mno = movieService.modify(movieDto);

        rttr.addAttribute("mno", mno);
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/movie/read";
    }

    @PostMapping("/remove")
    public String postRemove(@RequestParam Long mno, @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("영화 삭제 요청 {}", mno);

        movieService.delete(mno);

        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/movie/list";

    }

    @GetMapping("/create")
    public void getCreate(MovieDTO movieDto,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("영화 작성 폼 요청");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String postCreate(@Valid MovieDTO movieDto, BindingResult result,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("영화 등록 {}", movieDto);

        if (result.hasErrors()) {
            log.info(result.toString());
            log.info(result.getFieldValue("movieImageDTOs"));
            log.info(result.getRawFieldValue("movieImageDTOs"));
            log.info(result.getFieldError());
            return "/movie/create";
        }

        Long mno = movieService.register(movieDto);

        rttr.addAttribute("mno", mno);
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/movie/read";

    }

}
