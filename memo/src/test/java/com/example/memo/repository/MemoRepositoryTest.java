package com.example.memo.repository;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void insert() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Text Memo " + i)
                    .build();

            memoRepository.save(memo);
        });
    }

    @Test
    public void read() {
        // 6번 메모 가져오기
        Memo memo = memoRepository.findById(27L).get();
        System.out.println(memo);

        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(i -> System.out.println(i));
    }

    @Test
    public void update() {
        // 7번 메모 가져오기
        Memo memo = memoRepository.findById(28L).get();
        memo.setMemoText("안녕하세요");
        memoRepository.save(memo);
    }

    @Test
    public void delete() {
        // 마지막 번호 삭제
        memoRepository.deleteById(31L);
    }

}
