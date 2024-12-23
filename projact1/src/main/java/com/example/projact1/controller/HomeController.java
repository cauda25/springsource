package com.example.projact1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.projact1.dto.LoginDTo;
import com.example.projact1.dto.SampleDTO;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        log.info("index 요청");
        model.addAttribute("name", "hong");

        LoginDTo lDto = new LoginDTo("hong456", "hong456");
        model.addAttribute("login", lDto);

        // SampleDTO sampleDTO = new SampleDTO();
        // sampleDTO.setId(1);
        // sampleDTO.setFirst("Adam");
        // sampleDTO.setLast("Savage");
        // sampleDTO.setRegDateTime(LocalDateTime.now());

        SampleDTO sDto = SampleDTO.builder()
                .id(1L)
                .first("Adam")
                .last("Savage")
                .regDateTime(LocalDateTime.now())
                .build();
        model.addAttribute("sampleDTO", sDto);

        List<SampleDTO> list = new ArrayList<>();
        LongStream.rangeClosed(1, 10)
                .forEach(i -> {
                    SampleDTO dto = SampleDTO.builder()
                            .id(i)
                            .first("first" + i)
                            .last("last" + i)
                            .regDateTime(LocalDateTime.now())
                            .build();
                    list.add(dto);
                });

        model.addAttribute("list", list);

        model.addAttribute("now", new Date());
        model.addAttribute("price", 123456789);
        model.addAttribute("options", Arrays.asList("AAAA", "BBBB", "CCCC", "DDDD"));
        model.addAttribute("title", "This is a just sample");
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public String getMethodName() {
        return "반갑꼬리";
    }

}
