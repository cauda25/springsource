package com.example.project2.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project2.entify.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // save(Entity) : insert,update
    // findById(pk) : pk 조회
    // findAll() : 전체 조회
    // deleteById(pk) : 하나 삭제
    // delete(Entity) : 하나 삭제

    // Spring date sql 쿼리 메소드 사용
    // where title = ?
    // List<Board> findByTitle(String title);

    // where title like ?
    // List<Board> findByTitleLike(String title);

    // where title like '%title'
    // List<Board> findByTitleStartingWith(String title);

    // where writer like 'writer%'
    // List<Board> findByTitleEndingWith(String writer);

    // where writer like '%writer%'
    // List<Board> findByTitleContaining(String writer);

    // where writer like '%writer%' or title like '%title'
    // List<Board> findByWriterContainingOrTitleContaining(String writer, String
    // title);

    // where title like '%title%' and id >10
    // List<Board> findByTitleContainingAndIdGreaterThan(String title, Long id);

    // id >10 order by id desc
    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

    // @Query 어노테이션 사용
    // 실 sql은 아님
    // select * from board b where b.writer like '%user%' and b.id > 0 order by b.id
    // desc
    // @Query("SELECT b FROM Board b WHERE b.writer LIKE %:writer% AND b.id > 0
    // ORDER BY b.id DESC")
    @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% AND b.id > 0 ORDER BY b.id DESC")
    List<Board> findByWriterList(String writer);

    // @Query("SELECT b FROM Board b WHERE b.title = :title")
    // @Query("SELECT b FROM Board b WHERE b.title LIKE %:title%")
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1%")
    List<Board> findByTitle(String title);

    @Query("SELECT b FROM Board b WHERE b.writer LIKE %?1% OR b.title LIKE %?2%")
    List<Board> findByWriterContainingOrTitleContaining(String writer, String title);
}
