package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.GuestBook;

public interface GuestBookService {

    // 등록
    Long resister(GuestBookDTO dto);

    // 조회
    GuestBookDTO read(Long gno);

    // 전체조회
    PageResultDTO<GuestBookDTO, GuestBook> list(PageRequestDTO requestDTO);

    // 수정
    Long update(GuestBookDTO dto);

    // 삭제
    void delete(Long gno);

    // dtoToEntity
    public default GuestBook dtoToEntity(GuestBookDTO gDto) {
        GuestBook guestBook = GuestBook.builder()
                .gno(gDto.getGno())
                .writer(gDto.getWriter())
                .title(gDto.getTitle())
                .content(gDto.getContent())
                .build();
        return guestBook;

    }

    // entityToDto
    public default GuestBookDTO entityToDto(GuestBook entity) {
        return GuestBookDTO.builder()
                .gno(entity.getGno())
                .writer(entity.getWriter())
                .title(entity.getTitle())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
}
