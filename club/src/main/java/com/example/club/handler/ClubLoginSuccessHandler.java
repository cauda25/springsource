package com.example.club.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.club.dto.ClubAuthMemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {

    // 소셜 로그인 경우에 URL을 다르게 지정
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인 성공 후 원래 시작했던 피이지로 돌려보냄(기본 움직임)
    // 각 ROlE 에 따라 가는 경로를 다르게 지정

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        ClubAuthMemberDTO clubAuthMemberDTO = (ClubAuthMemberDTO) authentication.getPrincipal();

        List<String> roleNames = new ArrayList<>();
        clubAuthMemberDTO.getAuthorities().forEach(auth -> roleNames.add(auth.getAuthority()));

        // social에서 왔는지 파악
        if (clubAuthMemberDTO.isFromSocial()) {
            boolean result = passwordEncoder.matches("1111", clubAuthMemberDTO.getPassword());
            if (result) {
                redirectStrategy.sendRedirect(request, response, "/users/modify?from=social");
            }
        } else { // 일반 로그인
            if (roleNames.contains("ROLE_ADMIN") || roleNames.contains("ROLE_MANAGER")) {
                response.sendRedirect("/users/admin");
                return;
            }

            if (roleNames.contains("ROLE_USER")) {
                response.sendRedirect("/users/member");
                return;
            }
            response.sendRedirect("/");
        }

    }

}
