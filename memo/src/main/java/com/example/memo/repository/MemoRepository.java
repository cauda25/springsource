package com.example.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // // where meno < 5
    List<Memo> findByMenoLessThan(Long meno);

    // // where meno < 10 order by meno desc
    // List<Memo> findbyMenoLessThanOrderByMemoDesc(Long meno);

    // // where memo >=50 memo <= 100 order by meno desc
    // List<Memo> findbyMenoBetween(Long start, Long end);
}
