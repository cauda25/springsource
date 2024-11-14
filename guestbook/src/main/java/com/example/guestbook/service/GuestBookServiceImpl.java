package com.example.guestbook.service;

import java.util.function.Function;
import java.util.stream.LongStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookRepository;

import groovyjarjarantlr4.v4.parse.ANTLRParser.ruleEntry_return;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long resister(GuestBookDTO dto) {
        // GuestBook guestBook = GuestBook.builder()
        // .writer(dto.getWriter())
        // .title(dto.getTitle())
        // .content(dto.getContent())
        // .build();
        // return guestBookRepository.save(guestBook).getGno();
        return guestBookRepository.save(dtoToEntity(dto)).getGno();
    }

    @Override
    public GuestBookDTO read(Long gno) {
        return entityToDto(guestBookRepository.findById(gno).get());
        // 해석
        // GuestBook result = guestBookRepository.findById(gno).get();
        // GuestBookDTO dto = entityToDto(result);
        // return dto;
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> list(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = guestBookRepository
                .findAll(guestBookRepository.makePredicate(requestDTO.getType(), requestDTO.getKeyword()),
                        pageable);

        Function<GuestBook, GuestBookDTO> fn = (en -> entityToDto(en));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long update(GuestBookDTO dto) {
        GuestBook guestbook = guestBookRepository.findById(dto.getGno()).get();
        guestbook.setWriter(dto.getWriter());
        guestbook.setTitle(dto.getTitle());
        guestbook.setContent(dto.getContent());
        return guestBookRepository.save(guestbook).getGno();
    }

    @Override
    public void delete(Long gno) {
        guestBookRepository.deleteById(gno);
    }

}
