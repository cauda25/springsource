package com.example.guestbook.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestbook.dto.GuestBookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
    private final GuestBookService guestBookService;

    @GetMapping("/list")
    public void getMethodName(@ModelAttribute(name = "rqdto") PageRequestDTO requestDTO, Model model) {
        log.info("list 요청");
        PageResultDTO<GuestBookDTO, GuestBook> result = guestBookService.list(requestDTO);
        model.addAttribute("result", result);

    }

}
