package com.example.board.repositoryTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Transactional
    @Test
    public void testReadBoard() {
        Board br = boardRepository.findById(100L).get();
        System.out.println(br);

        // 객체 그래프 탐색 : Board,Member (N:1)
        System.out.println(br.getWriter());
    }

    @Transactional
    @Test
    public void testReadReply() {
        Reply re = replyRepository.findById(100L).get();
        System.out.println(re);

        // 객체 그래프 탐색 : Reply,Board (N:1)
        // 원본글 조회
        System.out.println(re.getBoard());
    }

    @Transactional
    @Test
    public void testReadBoardReply() {
        Board br = boardRepository.findById(99L).get();
        System.out.println(br);

        System.out.println(br.getReplies());

    }

    @Test
    public void testJoin() {
        List<Object[]> result = boardRepository.list();

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // Board board = (Board) objects[0];
            // Member member = (Member) objects[1];
            // Long replyCnt = (Long)objects[3];
        }

    }

    @Test
    public void testJoinList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        // Pageable pageable = PageRequest.of(0, 10,
        // Sort.by("bno").descending()
        // .and(Sort.by("title").descending()));

        Page<Object[]> result = boardRepository.list("tc", "content", pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));

        }

    }

    @Test
    public void testRow() {

        Object[] objects = boardRepository.getBoardByBno(100L);
        System.out.println(Arrays.toString(objects));
    }

    @Transactional
    @Test
    public void testReplyRemove() {
        // replyRepository.deleteByBno(4L);
        boardRepository.deleteById(2L);
    }

}
