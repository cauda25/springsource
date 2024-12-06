package com.example.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mybatis.dto.PageRequestDTO;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String getMethodName(@ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model,
            RedirectAttributes rttr) {
        log.info("화면 출력");
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());
        return "index";
    }

}
