package com.example.mart;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.SportsMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LockerRepositoryTest {
    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private SportsMemberRepository memberRepository;

    @Test
    public void testLockerInsert() {
        // locker 4
        IntStream.rangeClosed(1, 8).forEach(i -> {
            Locker locker = Locker.builder().name("Locker" + i).build();
            lockerRepository.save(locker);
        });

        // member 4
        LongStream.rangeClosed(1, 8).forEach(i -> {
            Locker locker = Locker.builder().id(i).build();
            SportsMember member = SportsMember.builder().name("user" + i).locker(locker).build();
            memberRepository.save(member);

        });
    }

    @Transactional
    @Test
    public void testMemberRead() {
        // 회원 조회(+Locker 조회)
        SportsMember sportsMember = memberRepository.findById(3L).get();
        System.out.println(sportsMember);

        // 객체 그래프 조회
        System.out.println(sportsMember.getLocker());

        // 전체 회원 조회
        memberRepository.findAll().forEach(member -> {
            System.out.println(member);
            System.out.println(member.getLocker());
        });
    }

    @Test
    public void testLockerRord() {
        // 전체 locker 조회(+회원조회)
        lockerRepository.findAll().forEach(locker -> {
            System.out.println(locker);
            System.out.println(locker.getSportsMember());
        });
    }
}
