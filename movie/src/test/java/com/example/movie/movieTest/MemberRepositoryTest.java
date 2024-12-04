package com.example.movie.movieTest;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testMemberInsert() {
        // LongStream.rangeClosed(1, 50).forEach(i -> {
        Member member = Member.builder()
                .email("admin@email.com")
                .password(passwordEncoder.encode("1111"))
                .nickname("admin")
                .memberRole(MemberRole.ADMIN)
                .build();
        memberRepository.save(member);
        // });

    }

    @Test
    public void testUpdate() {
        Member member = memberRepository.findById(2L).get();
        member.setNickname("dragon");
        memberRepository.save(member);
    }

    @Transactional
    @Test
    public void testUpdate2() {
        memberRepository.updateNickname("test", "usermail3@email.com");

    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {

        reviewRepository.deleteByMember(Member.builder().mmid(49L).build());
        memberRepository.deleteById(49L);
    }
}
