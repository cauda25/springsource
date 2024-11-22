package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.repository.search.SearchBoardRepoditory;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void getMethodName(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("list 요청");
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(requestDTO);
        model.addAttribute("result", result);
    }

    // 주소줄 남음 => SecurityConfig 해결 하는 방법
    @GetMapping({ "/read", "/modify" })
    public void getMethodName(@RequestParam Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
            Model model) {
        log.info("상세조회 {}", bno);

        BoardDTO dto = boardService.read(bno);
        model.addAttribute("dto", dto);
    }

    // 로그인 사용자 == 작성자
    @PreAuthorize("authentication.name == #dto.writerEmail")
    @PostMapping("/modify")
    public String postMethodName(BoardDTO dto, @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {

        log.info("수정 {}", dto);

        Long bno = boardService.update(dto);
        rttr.addAttribute("bno", dto.getBno());
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());
        return "redirect:read";
    }

    // @Transactional
    @PreAuthorize("authentication.name == #writerEmail")
    @PostMapping("/remove")
    public String postRemove(@RequestParam Long bno, String writerEmail,
            @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {
        log.info("삭제 요청 {}", bno);
        boardService.delete(bno);
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public void getMethodName(@ModelAttribute("dto") BoardDTO dto,
            @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO) {
        log.info("작성 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postMethodName(@Valid @ModelAttribute("dto") BoardDTO dto, BindingResult result,
            @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {
        log.info("작성 등록 요청 {}", dto);
        if (result.hasErrors()) {
            return "/board/create";
        }

        Long bno = boardService.register(dto);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:/board/read";
    }

}
