package com.example.book.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String getMethodName() {
        log.info("화면 출력");
        return "index";
    }

}
