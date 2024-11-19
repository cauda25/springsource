package com.example.security.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootTest
public class SecurityTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoder() {
        // 사용자 비밀번호
        String password = "1111";
        // encode() : 비밀번호 암호화
        String encodePass = passwordEncoder.encode(password);
        System.out.println("raw password " + password + ", encode password " +
                encodePass);

        // metches() : 원래 비밀번호와 암호화 된 비밀번호의 일치여부
        System.out.println(passwordEncoder.matches(password, encodePass));
        System.out.println(passwordEncoder.matches("2222", encodePass));
    }

    @Test
    UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user1")
                .password("c2626b60-66e2-4fe1-81aa-c831b64b9cdf")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
