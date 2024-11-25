package com.example.movie.movieTest;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;
import com.example.movie.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testMemberInsert() {
        LongStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder()
                    .email("usermail" + i + "@email.com")
                    .password(passwordEncoder.encode("1111"))
                    .nickname("nickname" + i)
                    .memberRole(MemberRole.MEMBER)
                    .build();
            memberRepository.save(member);
        });

    }
}
