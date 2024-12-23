package com.example.club.repositoryTest;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.club.entity.ClubMember;
import com.example.club.entity.constant.ClubRole;
import com.example.club.repository.ClubMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ClubMemberRepositoryTest {
    @Autowired
    private ClubMemberRepository clubMemberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@email.com")
                    .name("user" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();
            clubMember.addMemberRole(ClubRole.USER);

            if (i > 80) {
                clubMember.addMemberRole(ClubRole.MENAGER);
            }
            if (i > 90) {
                clubMember.addMemberRole(ClubRole.ADMIN);
            }
            clubMemberRepository.save(clubMember);
        });
    }

    // @Transactional
    @Test
    public void testFind() {
        // 회원조회
        // System.out.println(clubMemberRepository.findById("user17@email.com").get());

        System.out.println(clubMemberRepository.findByEmailAndFromSocial("user17@email.com", false));
    }
}
