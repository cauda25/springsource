package com.example.board.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class BoradServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    // private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long register(BoardDTO dto) {

        return boardRepository.save(dtoToEntity(dto)).getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Page<Object[]> result = boardRepository.list(requestDTO.getType(), requestDTO.getKeyword(),
                requestDTO.getPageable(Sort.by("bno").descending()));

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO read(Long bno) {
        Object[] objects = boardRepository.getBoardByBno(bno);
        return entityToDto((Board) objects[0], (Member) objects[1], (Long) objects[2]);
    }

    @Override
    public Long update(BoardDTO dto) {
        // Board board = boardRepository.findById(dto.getBno()).get();
        // board.setTitle(dto.getTitle());
        // board.setContent(dto.getContent());
        // return boardRepository.save(board).getBno();

        return boardRepository.save(dtoToEntity(dto)).getBno();

    }

    @Transactional
    @Override
    public void delete(Long bno) {
        // 댓글
        replyRepository.deleteByBno(bno);
        // 원본글 삭제
        boardRepository.deleteById(bno);
    }

}
