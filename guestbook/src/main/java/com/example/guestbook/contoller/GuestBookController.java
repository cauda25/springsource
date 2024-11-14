package com.example.guestbook.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
    private final GuestBookService guestBookService;

    @GetMapping("/list")
    public void getMethodName(@ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("list 요청");
        PageResultDTO<GuestBookDTO, GuestBook> result = guestBookService.list(requestDTO);
        model.addAttribute("result", result);

    }

    @GetMapping({ "/read", "/modify" })
    public void getMethodName(@RequestParam Long gno, @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            Model model) {
        log.info("상세조회 {}", gno);
        GuestBookDTO dto = guestBookService.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postMethodName(GuestBookDTO dto, @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {
        log.info("수정 요청 {}", dto);
        long gno = guestBookService.update(dto);

        rttr.addAttribute("gno", gno);
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());
        // 수정 완료 후 상세 조회
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postMethodName(@RequestParam Long gno, @ModelAttribute(name = "requestDTO") PageRequestDTO requestDTO,
            RedirectAttributes rttr) {

        log.info("guestbook 삭제 {}", gno);
        guestBookService.delete(gno);
        rttr.addAttribute("page", requestDTO.getPage());
        rttr.addAttribute("size", requestDTO.getSize());
        rttr.addAttribute("type", requestDTO.getType());
        rttr.addAttribute("keyword", requestDTO.getKeyword());
        // 전체 목록으로 이동
        return "redirect:list";
    }

    @GetMapping("/register")
    public void getMethodName(@ModelAttribute("dto") GuestBookDTO dto) {
        log.info("작성 폼 요청");
    }

    @PostMapping("/register")
    public String postMethodName(@Valid @ModelAttribute("dto") GuestBookDTO dto, BindingResult result,
            RedirectAttributes rttr) {
        log.info("작성 등록 요청 {}", dto);
        if (result.hasErrors()) {
            return "/guestbook/register";
        }

        Long gno = guestBookService.resister(dto);
        rttr.addFlashAttribute("msg", gno);
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", 20);
        rttr.addAttribute("type", "");
        rttr.addAttribute("keyword", "");

        return "redirect:list";
    }

}
