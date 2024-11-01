package com.example.project3.repository;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TeamMemberRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createTest() {
        Team team = Team.builder()
                .id("team1")
                .name("팀1")
                .build();

        teamRepository.save(team);

        team = Team.builder()
                .id("team2")
                .name("팀2")
                .build();

        teamRepository.save(team);
    }

    @Test
    public void createMemberTest() {
        Team team1 = teamRepository.findById("team1").get();
        Team team2 = Team.builder()
                .id("team2")
                .build();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .userName("성츈향" + i)
                    .team(team2)
                    .build();
            memberRepository.save(member);
        });
        IntStream.rangeClosed(5, 10).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .userName("성츈향" + i)
                    .team(team1)
                    .build();
            memberRepository.save(member);
        });

    }

    @Test
    public void selecttest() {
        // 회원찾기

        Member member = memberRepository.findById("user1").get();
        System.out.println(member);

        // 팀 정보 찾기
        System.out.println(member.getTeam());
        // 팀명 찾기
        System.out.println(member.getTeam().getName());
    }

    @Test
    public void memberEqualTeamTest() {
        memberRepository.findMemberEqualTeam("팀1").forEach(m -> System.out.println(m));
    }

    @Test
    public void updateTest() {
        // user6의 팀 변경하기
        Member member = memberRepository.findById("user6").get();

        Team team2 = teamRepository.findById("team2").get();
        member.setTeam(team2);

        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // team1 제거
        // 무결성 제약조건 위배 자식레코드 발견
        // teamRepository.deleteById("team1");

        // 외래키 제약조건에서는 자식부터 삭제
        // 자식의 팀 변경 또는 삭제 우선

        Team team = Team.builder().id("team1").build();
        List<Member> list = memberRepository.findByTeam(team);
        // list.forEach(m -> System.out.println(m));

        Team team2 = teamRepository.findById("team2").get();
        list.forEach(m -> {
            m.setTeam(team2);
            memberRepository.save(m);
        });

        // team1 제거
        teamRepository.deleteById("team1");
    }

    // member 삽입하면서 team 삽입이 가능한가
    // sql 1) 부모삽입 2) 자식삽입
    // jps에서는
    @Test
    public void memberAndTeamInsertTest() {
        Team team = Team.builder().id("team3").name("팀3").build();
        Member member = Member.builder().id("user11").userName("홍길동").team(team).build();
        memberRepository.save(member);

    }

    @Test
    public void memberAndTeamUpdateTest() {
        Team team = teamRepository.findById("team3").get();
        team.setName("victory");

        Member member = Member.builder().id("user11").userName("홍길동").team(team).build();
        memberRepository.save(member);

    }

    // 양방향
    @Transactional
    @Test
    public void selectMemberTest() {
        // 팀 찾기
        Team team = teamRepository.findById("team2").get();

        // left join를 하지 않음 => member 정보 없음
        team.getMembers().forEach(m -> { // org.hibernate.LazyInitializationException: initialize proxy - no Session
            // 팀 정보 출력
            System.out.println(m);
            // 팀에 속한 member의 이름 출력
            System.out.println(m.getUserName());
        });
    }

    @Test
    public void teamAndMemberInsertTest() {
        Team team = Team.builder().id("team3").name("팀3").build();
        Member member = Member.builder().id("user12").userName("수선화").team(team).build();
        team.getMembers().add(member);
        teamRepository.save(team);

        // 원래 방식
        // Team team = Team.builder().id("team4").name("팀4").build();
        // teamRepository.save(team);
        // Member member =
        // Member.builder().id("user12").userName("수선화").team(team).build();
        // memberRepository.save(member);
    }

}
