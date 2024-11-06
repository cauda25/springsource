package com.example.memo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.memo.dto.MemoDTO;
import com.example.memo.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/create")
    public void getCreateForm() {
        log.info("메모 작성 폼 요청");
    }

    @PostMapping("/create")
    public String posCreate(@Valid MemoDTO mDTO, BindingResult result, RedirectAttributes rttr) {
        log.info("메모 작성 {}", mDTO);

        // 유효성 검증
        if (result.hasErrors()) {
            return "/memo/create";
        }

        Long mmo = memoService.create(mDTO);

        rttr.addFlashAttribute("msg", mmo);
        return "redirect:list";
    }

    @GetMapping("/list")
    public void getList(Model model) {
        log.info("메모 전체 목록 요청");
        List<MemoDTO> list = memoService.list();
        model.addAttribute("list", list);
    }

    @GetMapping(path = { "/read", "/update" })
    public void getMethodName(@RequestParam Long memo, Model model) {
        log.info("메모 조회 {}", memo);

        MemoDTO dto = memoService.read(memo);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/update")
    public String postMethodName(MemoDTO dto, RedirectAttributes rttr) {
        log.info("수정 요청 {}", dto);

        Long mmo = memoService.update(dto);
        rttr.addFlashAttribute("msg", mmo);
        return "redirect:list";
    }

    @GetMapping("/remove")
    public String getMethodName(@RequestParam Long memo, RedirectAttributes rttr) {
        log.info("메모 삭제 요청 {}", memo);
        memoService.delete(memo);
        rttr.addFlashAttribute("msg", memo + "번 메모가 삭제되었습니다.");
        return "redirect:list";
    }

}
