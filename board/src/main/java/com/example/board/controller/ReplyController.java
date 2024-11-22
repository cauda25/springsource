package com.example.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDTO;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RequestMapping("/replies")
@Log4j2
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/board/{bno}")
    public ResponseEntity<List<ReplyDTO>> getList(@PathVariable Long bno) {
        log.info("{} 댓글 요청" + bno);
        List<ReplyDTO> replies = replyService.list(bno);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    // 작성자 == 로그인 사용자
    @PreAuthorize("authentication.name == #dto.replyerEmail")
    @PostMapping("/new")
    public ResponseEntity<Long> postRegister(@RequestBody ReplyDTO dto) {
        log.info("댓글 작성 {}", dto);

        Long rno = replyService.register(dto);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }

    @GetMapping("/{rno}")
    public ResponseEntity<ReplyDTO> getRow(@PathVariable Long rno) {
        log.info("댓글 상세조회 {}", rno);
        ReplyDTO dto = replyService.read(rno);
        return new ResponseEntity<ReplyDTO>(dto, HttpStatus.OK);
    }

    @PreAuthorize("authentication.name == #dto.replyerEmail")
    @PutMapping("/{rno}")
    public ResponseEntity<Long> putMethodName(@PathVariable Long rno, @RequestBody ReplyDTO dto) {
        log.info("댓글 수정 {},{}", rno, dto);

        dto.setRno(rno);
        rno = replyService.modify(dto);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }

    @PreAuthorize("authentication.name == #dto.replyerEmail")
    @DeleteMapping("/{rno}")
    public ResponseEntity<Long> deleteReply(@PathVariable Long rno, @RequestBody ReplyDTO dto) {
        log.info("댓글 삭제 {}", rno);

        replyService.remove(rno);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);

    }

}
