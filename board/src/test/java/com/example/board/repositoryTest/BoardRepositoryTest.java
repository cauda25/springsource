package com.example.board.repositoryTest;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertMember() {
        // 30명

        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .name("member" + i)
                    .password("12345")
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void insertBoard() {
        // 100개

        LongStream.rangeClosed(1, 100).forEach(i -> {

            int num = (int) (Math.random() * 30) + 1;
            Member member = Member.builder().email("user" + num + "@gmail.com").build();

            Board board = Board.builder()
                    .content("content..." + i)
                    .title("title..." + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void insertReply() {
        // 100개
        LongStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .replyer("guest" + i)
                    .text("test" + i)
                    .board(board)
                    .build();

            replyRepository.save(reply);
        });
    }
}
