package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.movie.dto.PageRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
@Controller
public class MemberController {

    @GetMapping("/login")
    public void getMethodName(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("로그인 요청");
    }

}
