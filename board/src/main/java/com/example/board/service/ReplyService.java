package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

public interface ReplyService {

    Long register(ReplyDTO dto);

    List<ReplyDTO> list(Long bno);

    ReplyDTO read(Long rno);

    Long modify(ReplyDTO dto);

    void remove(Long rno);

    // entity => dto
    public default ReplyDTO entityToDto(Reply entity) {

        ReplyDTO dto = ReplyDTO.builder()
                .rno(entity.getRno())
                .replyerEmail(entity.getReplyer().getEmail())
                .replyerName(entity.getReplyer().getName())
                .text(entity.getText())
                .bno(entity.getBoard().getBno())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();

        return dto;
    }

    // dto => entity
    public default Reply dtoToEntity(ReplyDTO dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        Member member = Member.builder().email(dto.getReplyerEmail()).build();

        Reply entity = Reply.builder()
                .rno(dto.getRno())
                .replyer(member)
                .text(dto.getText())
                .board(board)
                .build();

        return entity;
    }
}
