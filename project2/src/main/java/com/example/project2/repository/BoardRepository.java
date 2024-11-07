package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.entify.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // save(Entity) : insert,update
    // findById(pk) : pk 조회
    // findAll() : 전체 조회
    // deleteById(pk) : 하나 삭제
    // delete(Entity) : 하나 삭제

    // where title = ?
    List<Board> findByTitle(String title);

    // where title like ?
    List<Board> findByTitleLike(String title);
}
