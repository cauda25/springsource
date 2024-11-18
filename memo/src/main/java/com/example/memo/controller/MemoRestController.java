package com.example.memo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.memo.dto.MemoDTO;
import com.example.memo.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RequestMapping("/rest")
@Log4j2
@RestController // 화면단은 어떤 것이 들어와도 상관없음
public class MemoRestController {

    private final MemoService memoService;

    @GetMapping("/list")
    public List<MemoDTO> getList() {
        log.info("메모 전체 목록 요청");
        List<MemoDTO> list = memoService.list();
        return list;
    }

    @GetMapping("/{memo}")
    public MemoDTO getMethodName(@PathVariable Long memo) {
        log.info("메모 조회 {}", memo);
        MemoDTO dto = memoService.read(memo);
        return dto;

    }

    // RequestBody : json => 객체
    @PostMapping("/create")
    public ResponseEntity<String> posCreate(@RequestBody MemoDTO mDTO) {
        log.info("메모 작성 {}", mDTO);

        Long mmo = memoService.create(mDTO);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // rest 추가되는 method : PUT(patch) / DELETE
    @PutMapping("/{memo}")
    public ResponseEntity<String> postMethodName(@PathVariable Long memo, @RequestBody MemoDTO dto) {
        log.info("수정 요청 {}", dto);

        dto.setMemo(memo);
        memoService.update(dto);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/{memo}")
    public ResponseEntity<String> getRemov(@PathVariable Long memo) {
        log.info("메모 삭제 요청 {}", memo);
        memoService.delete(memo);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

}
