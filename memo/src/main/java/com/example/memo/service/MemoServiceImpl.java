package com.example.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Override
    public Long create(MemoDTO mDTO) {
        // Memo memo = Memo.builder().memoText(mDTO.getMemoText()).build();
        Memo memo = dtoToEntity(mDTO);
        return memoRepository.save(memo).getMeno();
    }

    @Override
    public MemoDTO read(Long id) {
        Memo memo = memoRepository.findById(id).get();
        return entityToDto(memo);
    }

    @Override
    public List<MemoDTO> list() {
        List<Memo> list = memoRepository.findAll();

        return list.stream().map(memo -> entityToDto(memo)).collect(Collectors.toList());
    }

    @Override
    public Long update(MemoDTO mDTO) {
        // Memo memo = memoRepository.findById(m).get();
        // memo.setMemoText("안녕하세요");
        Memo memo = dtoToEntity(mDTO);
        return memoRepository.save(memo).getMeno();
    }

    @Override
    public void delete(Long id) {
        memoRepository.deleteById(id);
    }

}
