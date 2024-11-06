package com.example.memo.service;

import java.util.List;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Memo;

public interface MemoService {
    // crud메소드
    Long create(MemoDTO mDTO);

    MemoDTO read(Long id);

    List<MemoDTO> list();

    Long update(MemoDTO mDTO);

    void delete(Long id);

    // dto ==> entity
    public default Memo dtoToEntity(MemoDTO mDTO) {
        return Memo.builder().meno(mDTO.getMemo()).memoText(mDTO.getMemoText()).build();

    }

    // entitu ==> dto
    public default MemoDTO entityToDto(Memo memo) {
        return MemoDTO.builder()
                .memo(memo.getMeno())
                .memoText(memo.getMemoText())
                .createDateTime(memo.getCreateDateTime())
                .lastModifyDateTime(memo.getLastModifyDateTime())
                .build();
    }
}
