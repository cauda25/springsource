package com.example.projact1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.projact1.dto.LoginDTo;
import com.example.projact1.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    // 양식 페이지 보여주기
    @GetMapping("/login")
    public void getLogin(@ModelAttribute("login") LoginDTo loginDTO) {
        log.info("login 페이지 요청");
    }

    // // 가져오기
    // @PostMapping("/login")
    // public void postLogin(HttpServletRequest request) {
    // log.info("login 요청 - 사용자 입력값 요청");
    // String userid = request.getParameter("userid");
    // String password = request.getParameter("password");

    // log.info("user : {}, password : {}", userid, password);
    // }
    // 양식 페이지 보여주기

    // 가져오기
    // @PostMapping("/login")
    // public void postLogin(String userid, String password) {
    // log.info("login 요청 - 사용자 입력값 요청");

    // log.info("user : {}, password : {}", userid, password);
    // }

    @PostMapping("/login")
    // @ModelAttribute == model.addAttribute
    public String postLogin(@Valid @ModelAttribute("login") LoginDTo loginDTO, BindingResult result) {
        log.info("login 요청 - 사용자 입력값 요청");
        log.info("user : {}, password : {}", loginDTO.getUserid(),
                loginDTO.getPassword());

        if (result.hasErrors()) {
            return "/member/login";
        }

        return "index";
    }

    @GetMapping("/register")
    public void getRegister(MemberDTO mDto) {
        log.info("register");
    }

    // post /return => 로그인 페이지
    @PostMapping("/register")
    public String postRegister(@Valid MemberDTO mDto, BindingResult result) {
        log.info(mDto);

        if (result.hasErrors()) {
            return "/member/register";
        }

        // 1. 입력값 가져오기
        // 2. 서비스 호출 후 결과 받기
        // 3. model.addAttribute()
        // 4. 페이지 이동

        return "redirect:/member/login";
    }

}
