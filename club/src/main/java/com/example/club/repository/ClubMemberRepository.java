package com.example.club.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.club.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    // where cm1_0.email=? and cm1_0.from_social = ?
    @EntityGraph(attributePaths = { "roles" }, type = EntityGraphType.LOAD)
    Optional<ClubMember> findByEmailAndFromSocial(String email, boolean fromSocial);

}
// @EntityGraph
// fatch 속성이 LAZY 인 경우 특정 기능에서만 EAGER로 동작하도록 설정
// attributePaths 에 표시한 속성만 EAGER로 처리
