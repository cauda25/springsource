package com.example.guestbook.service;

import java.util.function.Function;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long resister(GuestBookDTO dto) {
        return null;
    }

    @Override
    public GuestBookDTO read(Long gno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> list(PageRequestDTO requestDTO) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        Page<GuestBook> result = guestBookRepository
                .findAll(guestBookRepository.makePredicate(requestDTO.getType(), requestDTO.getKeyword()),
                        pageable);

        Function<GuestBook, GuestBookDTO> fn = (en -> entityToDto(en));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long update(GuestBookDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long gno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
