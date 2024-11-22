package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.MemberDTO;
import com.example.board.service.BoardUserDetilsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/member")
@Log4j2
@Controller
public class MemberController {

    private final BoardUserDetilsService boardUserDetilsService;

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getMethodName() {
        log.info("로그인 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void getRegister(MemberDTO dto) {
        log.info("로그인 요청");
    }

    @PostMapping("/register")
    public String postMethodName(@Valid MemberDTO dto, BindingResult result, RedirectAttributes rttr) {
        log.info("회원가입 폼 {}", dto);

        if (result.hasErrors()) {
            return "/member/register";
        }

        // 서비스 작업
        String name = "";
        try {
            name = boardUserDetilsService.register(dto);

        } catch (Exception e) {
            log.info(e.getMessage());
            rttr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/member/register";
        }

        rttr.addFlashAttribute("msg", name);
        return "redirect:/member/login";
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthentication() {

        // SecurityContext : 인증된 유저를 저장하는 곳
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication;
    }

}
