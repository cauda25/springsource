package com.example.club.controller;

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

import com.example.club.dto.ClubMemberDTO;
import com.example.club.service.ClubOAuthDetilsService;
import com.example.club.service.ClubUserDetilsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/users")
public class UserController {

    private final ClubUserDetilsService clubUserDetilsService;

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getMethodName() {
        log.info("로그인 폼 요청");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void getMember() {
        log.info("멤버 폼 요청");
    }

    @PreAuthorize("hasAnyRole('MANEGER' ,'ADMIN')")
    @GetMapping("/admin")
    public void getAdmin() {
        log.info("어드민 폼 요청");
    }

    @GetMapping("/register")
    public void getRegister(ClubMemberDTO dto) {
        log.info("회원가입 폼 요청");
    }

    @PostMapping("/register")
    public String postRegister(@Valid ClubMemberDTO dto, BindingResult result, RedirectAttributes rttr) {
        log.info("회원가입 폼 요청 {}", dto);
        if (result.hasErrors()) {
            return "/users/register";
        }

        String me = clubUserDetilsService.register(dto);
        rttr.addFlashAttribute("email", me);

        return "redirect:/users/login";
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthentication() {

        // SecurityContext : 인증된 유저를 저장하는 곳
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication;
    }

}
