package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.AuthMemberDTO;
import com.example.movie.dto.MemberDTO;
import com.example.movie.dto.PageRequestDTO;
import com.example.movie.dto.PasswordDTO;
import com.example.movie.service.MemberDetailsService;
import com.example.movie.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
@Controller
public class MemberController {

    public final MemberService memberService;

    public final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public void getMethodName(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("로그인 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public void getProfile(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("프로필 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public void getEdit(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("프로필 수정 폼 요청");
    }

    // 닉네임 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/nickname")
    public String postNickname(MemberDTO memberDTO) {
        log.info("닉네임 수정 {}", memberDTO);

        // email 가져오기
        Authentication authentication = getAuthentication();
        // MemberDTO 에 들어있는 값 접근 시
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();
        memberDTO.setEmail(authMemberDTO.getUsername());

        // SecurityContext 에 보관된 값 업데이트
        authMemberDTO.getMemberDTO().setNickname(memberDTO.getNickname());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        memberService.nicknameUpdate(memberDTO);

        return "redirect:/member/profile";

    }

    // 비밀번호 수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/password")
    public String postPass(PasswordDTO passwordDTO, HttpSession session,
            RedirectAttributes rttr) {
        log.info("비밀번호 수정 {}", passwordDTO);

        // 서비스 호출
        try {
            memberService.passwordUpdate(passwordDTO);
        } catch (Exception e) {
            // 실패 시 /edit

            e.printStackTrace();
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/edit";
        }
        // 성공 시 세션 해제 후 /login 이동
        session.invalidate();

        return "redirect:/member/login";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/leave")
    public void getLeave(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("회원 탈퇴 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/leave")
    public String postLeave(PasswordDTO passwordDTO, boolean check, HttpSession session,
            RedirectAttributes rttr) {
        log.info("회원탈퇴 {} ,{}", passwordDTO, check);

        if (!check) {
            rttr.addFlashAttribute("error", "체크 표시를 확인해 주세요");
            return "redirect:/member/leave";
        }
        // 서비스 작업
        try {
            memberService.leave(passwordDTO);
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/leave";
        }

        session.invalidate();

        return "redirect:/movie/list";
    }

    // 회원가입
    @GetMapping("/register")
    public void getRegi(MemberDTO memberDTO,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO) {
        log.info("회원가입");
    }

    @PostMapping("/register")
    public String postRegi(@Valid MemberDTO memberDTO, BindingResult result, boolean check,
            @ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, RedirectAttributes rttr, Model model) {

        log.info("회원가입 요청 {}", memberDTO);
        if (result.hasErrors()) {
            return "/member/register";
        }
        if (!check) {
            model.addAttribute("check", "약관에 동의하셔야합니다");
            return "/member/register";
        }
        memberService.register(memberDTO);

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
