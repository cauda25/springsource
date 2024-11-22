package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.board.entity.role.MemberRole;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // http://localhost:8080/ => permitAll()
    // board
    // /board/list, /board/read => permitAll()
    // /board/modify, /board/remove => 회원,작성자만 접근
    // /board/create => 회원만 가능

    // reply
    // /replies/board/1 => permitAll()
    // /replies/new => 회원만 가능
    // /replies/1 (remove,update) => 회원,작성자만 접근
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // static 아래 폴더 경로, 필터 무조건 통과 => 컨트롤러에서 접근 제한 설정
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/img/*", "/js/*", "/css/*", "/assets/*").permitAll()
                        // get방식으로 열어둔 modify 주소줄에서 경로 막기
                        .requestMatchers("/board/modify").hasAnyRole("MEMBER", "ADMIN")
                        .anyRequest().permitAll());
        http.formLogin(login -> login.loginPage("/member/login").permitAll());
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/"));

        return http.build();
    }

    // @Bean
    // RememberMeServices rememberMeServices(UserDetailsService userDetailsService)
    // {
    // RememberMeTokenAlgorithm rTokenAlgorithm = RememberMeTokenAlgorithm.SHA256;
    // TokenBasedRememberMeServices rememberMeServices = new
    // TokenBasedRememberMeServices("mykey", userDetailsService,
    // rTokenAlgorithm);
    // rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
    // rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
    // return rememberMeServices;

    // }

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
