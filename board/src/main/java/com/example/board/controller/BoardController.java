package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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

    @GetMapping({ "/read", "/modify" })
    public void getMethodName(@RequestParam Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
            Model model) {
        log.info("상세조회 {}", bno);

        BoardDTO dto = boardService.read(bno);
        model.addAttribute("dto", dto);
    }

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

}
