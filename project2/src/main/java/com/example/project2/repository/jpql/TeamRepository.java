package com.example.project2.repository.jpql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entify.jpql.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}