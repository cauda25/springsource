package com.example.projact1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class controller {

    // @RequestMapping(path = "/basic", method= RequestMethod.GET)
    // public void basic() {
    // log.info("basic 컨트롤러 동작");
    // }

    @GetMapping("/basic")
    public void basic() {
        log.info("basic 컨트롤러 동작");
    }

}
