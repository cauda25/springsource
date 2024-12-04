package com.example.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.PageRequestDTO;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "redirect:/movie/list";
    }

    @GetMapping("/access-denied")
    public String getAccess(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        // 403
        return "/except/denied";
    }

    @GetMapping("/error")
    public String get404(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        // 존재하지 않는 경로
        return "/except/url404";
    }

}
